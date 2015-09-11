package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/anatomy-of-a-synchronizer.html#notificationstrategy

Notification Strategy
Notify 1 specific of N waiting threads
Sometimes you may need to notify a specific thread. For instance if you need to guarantee that waiting threads are
notified in a specific order, be it the order they called the synchronizer in, or some prioritized order.
To achieve this each waiting thread must call wait() on its own object. When the notifying thread wants to notify
a specific waiting thread it will call notify() on the object this speicifc thread has called wait() on.

queue:          thread1,       thread2,          thread3
queue lockObj:  lockObj1(out), lockObj2,         lockObj3
lockingThread:                          thread1(unlock),  thread2(unlock),  thread3

output:
    Thread_1; ENTER lock; lockObj.id=1
    Thread_2; ENTER lock; lockObj.id=3
    Thread_3; ENTER lock; lockObj.id=2
#    Thread_2; ENTER while; isLocked=false; (queue[0] != queueObject)=true; queue.get(0).id=1; queueObject.id=3
#    Thread_1; ENTER while; isLocked=false; (queue[0] != queueObject)=false; queue.get(0).id=1; queueObject.id=1
  Thread_2; enter wait; lockObj.id=3
#    Thread_3; ENTER while; isLocked=false; (queue[0] != queueObject)=true; queue.get(0).id=1; queueObject.id=2
#    Thread_1 remove queueObject; queue.get(0).id=1; queueObject.id=1
  Thread_3; enter wait; lockObj.id=2
    Thread_1; unlock; lockObj.id=3
  Thread_1; exit notify; lockObj.id=3
Thread_1 finished; count=1
  Thread_2; EXIT wait; lockObj.id=3
#    Thread_2; ENTER while; isLocked=false; (queue[0] != queueObject)=false; queue.get(0).id=3; queueObject.id=3
#    Thread_2 remove queueObject; queue.get(0).id=3; queueObject.id=3
    Thread_2; unlock; lockObj.id=2
  Thread_2; exit notify; lockObj.id=2
Thread_2 finished; count=2
  Thread_3; EXIT wait; lockObj.id=2
#    Thread_3; ENTER while; isLocked=false; (queue[0] != queueObject)=false; queue.get(0).id=2; queueObject.id=2
#    Thread_3 remove queueObject; queue.get(0).id=2; queueObject.id=2
Thread_3 finished; count=3

 */
public class App {
    static class Counter {
        public int count = 0;
        FairLock lock = new FairLock();

        public void increment() {
            lock.lock();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;  // critical section
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finished; count=" + count);
        }
    }

    static class QueueObject {
        private int id = -1;
        private boolean isNotified = false;

        public QueueObject(int id) {
            this.id = id;
        }

        public synchronized void doWait() {
//            System.out.println("  "+Thread.currentThread().getName() + "; entered doWait; lockObj.id="+id);
            while (!isNotified) {
                try {
                    System.out.println("  " + Thread.currentThread().getName() + "; enter wait; lockObj.id=" + id);
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("  " + Thread.currentThread().getName() + "; EXIT wait; lockObj.id=" + id);
            }
            this.isNotified = false;
//            System.out.println("  "+Thread.currentThread().getName() + "; exit doWait; lockObj.id="+id);
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
            System.out.println("  " + Thread.currentThread().getName() + "; exit notify; lockObj.id=" + id);
        }
    }

    static int queueObjId = 1;

    static class FairLock {
        private boolean isLocked = false;
        private List<QueueObject> queue = new ArrayList<QueueObject>();

        public void lock() {
            QueueObject queueObject = new QueueObject(queueObjId++);
            System.out.println("    " + Thread.currentThread().getName() + "; ENTER lock; lockObj.id=" + queueObject.id);
            boolean isLockedForThisThread = true;
            synchronized (this) {
                queue.add(queueObject);
            }
            while (isLockedForThisThread) {
                System.out.println("#    " + Thread.currentThread().getName() + "; ENTER while;" + " isLocked=" + isLocked
                        + "; (queue[0] != queueObject)=" + (queue.get(0) != queueObject) + "; queue.get(0).id=" + queue.get(0).id + "; queueObject.id="+ queueObject.id);
                synchronized (this) {
                    // 0 || 0 = 0; 0 || 1 = 1; 1 || 0 = 1; 1 || 1 = 1
                    isLockedForThisThread = isLocked || queue.get(0) != queueObject; // non first objects in the queue need to wait
                    if (!isLockedForThisThread) {
                        isLocked = true;
                        System.out.println("#    " + Thread.currentThread().getName() + " remove queueObject" + "; queue.get(0).id=" + queue.get(0).id + "; queueObject.id=" + queueObject.id);
                        queue.remove(queueObject);
                        return;
                    }
                }
                queueObject.doWait();
            }
        }

        public synchronized void unlock() {
            isLocked = false;
            if (queue.size() > 0) {
                System.out.println("    " + Thread.currentThread().getName() + "; unlock; lockObj.id=" + queue.get(0).id);
                queue.get(0).doNotify();
            }
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 1; i <= 3; i++) {
            new Thread("Thread_" + i) {
                public void run() {
                    counter.increment();
                }
            }.start();
        }
    }
}

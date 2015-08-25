package com.company.app;

import java.util.*;

/*
For how to implement the FairLock, see Slipped Conditions realistic example at link below
http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html

output:
After added QueueObjects: queueObject.id=1
Thread_A_1 *isLocked = false
Thread_A_1 (queueObjects.get(0) != queueObject) = false; queueObject.id=1
Thread_A_1 mustWait = false
After removed QueueObjects:
After added QueueObjects: queueObject.id=2
Thread_A_2 *isLocked = true
Thread_A_2 (queueObjects.get(0) != queueObject) = false; queueObject.id=2
Thread_A_2 mustWait = true
Thread_A_2 queueObject.id=2 doWait()
After added QueueObjects: queueObject.id=2 queueObject.id=3
Thread_A_3 *isLocked = true
Thread_A_3 (queueObjects.get(0) != queueObject) = true; queueObject.id=3
Thread_A_3 mustWait = true
Thread_A_3 queueObject.id=3 doWait()
 Thread_A_1 queueObject.id=2 doNotify()  ## run following three lines Thread_A_2 code because of while loop
  Thread_A_1 increment finished; count: 1
Thread_A_2 *isLocked = false
Thread_A_2 (queueObjects.get(0) != queueObject) = false; queueObject.id=2
Thread_A_2 mustWait = false
After removed QueueObjects: queueObject.id=3
 Thread_A_2 queueObject.id=3 doNotify()
  Thread_A_2 increment finished; count: 2
Thread_A_3 *isLocked = false
Thread_A_3 (queueObjects.get(0) != queueObject) = false; queueObject.id=3
Thread_A_3 mustWait = false
After removed QueueObjects:
  Thread_A_3 increment finished; count: 3

 */
public class App
{
    static int count = 0;
    static class Counter{
        FairLock lock = new FairLock();
        public void increment() throws InterruptedException {
            lock.lock();
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("  "+Thread.currentThread().getName() + " increment finished; count: " + count);
        }
    }
    static int objId = 1;
    static class QueueObject {  // wait and notify signal
        public QueueObject(int id){
            this.id = id;
            objId++;
        }
        public int id;
        boolean isNotified = false;
        public synchronized void doWait(){
            while(!isNotified){
                try { wait();
                } catch (InterruptedException e) { }
            }
            isNotified = false;
        }
        public synchronized void doNotify(){
            notify();
            isNotified = true;
        }
    }
    static List<String> list = new ArrayList<String>();
    static class FairLock {
        boolean isLocked = false;
        Set<String> isLockedList = new HashSet<String>();
        Thread lockingThread = null;
        List<QueueObject> queueObjects = new ArrayList<QueueObject>();
        public void lock() throws InterruptedException {
            QueueObject queueObject;
            synchronized (this){
                list.add(Thread.currentThread().getName());
                queueObject = new QueueObject(objId);  // use wait and notify to avoid missed signal
                queueObjects.add(queueObject);  // ADD
                printQueueObjects("After added");
            }
            boolean mustWait = true;
            while(mustWait){
                synchronized (this){
                    isLockedList.add(Thread.currentThread().getName() + " isLocked=" + isLocked);
                    /*   must wait when following
                         do not need to wait when the lock is unlocked, and only one worker thread waiting
                    1, isLocked=true or
                    2, queueObject is not the ONLY worker thread waiting means (queueObjects.get(0) != queueObject) = true
                     */
                    mustWait = isLocked || queueObjects.get(0) != queueObject;
                    /*  if !mustWait which means we do not need to wait, so a thread can go in to do the work
                    1, remove queueObject from queueObjects
                    2, set isLocked to true
                    3, set lockingThread to currentThread
                     */
                    System.out.println(Thread.currentThread().getName()+ " *isLocked = "+isLocked);
                    System.out.println(Thread.currentThread().getName() + " (queueObjects.get(0) != queueObject) = "
                            + (queueObjects.get(0) != queueObject) + "; queueObject.id=" + queueObject.id);
                    System.out.println(Thread.currentThread().getName() + " mustWait = "+mustWait);
                    if(!mustWait){  // when mustWait is false,
                        if(queueObjects.size() > 0) queueObjects.remove(queueObject);  // REMOVE
                        printQueueObjects("After removed");
                        isLocked = true;
                        lockingThread = Thread.currentThread();
                        return; // no reason to continue when mustWait=false
                    }
                }
                /*
                1, lock the queueObject
                2, invoke wait() if mustWait=true
                 */
                synchronized (queueObject){
                    if(mustWait){
                        System.out.println(Thread.currentThread().getName() + " queueObject.id=" + queueObject.id + " doWait()");
                        queueObject.doWait();
                    }
                }
            }
        }
        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if(queueObjects.size() > 0){
                QueueObject queueObject = queueObjects.get(0);
                synchronized (queueObject){
                    System.out.println(" " + Thread.currentThread().getName() + " queueObject.id=" + queueObject.id + " doNotify()" );
                    queueObject.doNotify();
                }
            }
        }
        private void printQueueObjects(String msg) {
            System.out.print(msg + " QueueObjects: ");
            for(QueueObject queueObj : queueObjects)
                System.out.print("queueObject.id=" + queueObj.id + " ");
            System.out.println("");
        }
    }
    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        new Thread("Thread_A_" + 1){
            public void run(){
                try {
                    counter.increment();
                } catch (InterruptedException e) { }
            }
        }.start();
        new Thread("Thread_A_" + 2){
            public void run(){
                try {
                    counter.increment();
                } catch (InterruptedException e) { }
            }
        }.start();
        new Thread("Thread_A_" + 3){
            public void run(){
                try {
                    counter.increment();
                } catch (InterruptedException e) { }
            }
        }.start();
    }
}

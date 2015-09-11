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

output:
3

 */
public class App 
{
    static class Counter{
        FairLock lock = new FairLock();
        public int count = 0;
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;  // critical section
            lock.unlock();
        }
    }
    static class Signal {
        private boolean isLocked = false;
        public synchronized void doWait(){
            while(isLocked){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked = true;
        }
        public synchronized void doNotify(){
            isLocked = false;
            notify();
        }
    }
    static class FairLock{
        private boolean isLocked = false;
        private List<Signal> queue = new ArrayList<Signal>();
        public void lock(){
            Signal queueObj = new Signal();
            boolean isLockedForThisThread = true;
            synchronized (this){
                queue.add(queueObj); // add threads lockObj to the queue
            }
            while(isLockedForThisThread){
                synchronized (this){
                    isLockedForThisThread = isLocked || queue.get(0) != queueObj; // let go of the first thread
                    if(!isLockedForThisThread){
                        isLocked = true;
                        queue.remove(queueObj);
                        return;
                    }
                }
                queueObj.doWait();  // other threads need to wait
            }
        }
        public synchronized void unlock(){
            isLocked = false;
            if(queue.size() > 0){
                queue.get(0).doNotify(); // wake the first thread in the queue
            }
        }
    }
    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        for(int i = 1; i <= 3; i++){
            new Thread("Thread_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.count);
    }
}

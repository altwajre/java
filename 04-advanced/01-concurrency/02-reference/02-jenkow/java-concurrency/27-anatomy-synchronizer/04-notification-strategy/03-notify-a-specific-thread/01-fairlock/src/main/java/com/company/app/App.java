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
        public int count = 0;
        FairLock lock = new FairLock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {  }
            count++;
            lock.unlock();
        }
    }
    static class FairLock {
        List<Object> lockObjs = new ArrayList<Object>();
        boolean isLocked = false;
        public void lock(){
            Object lockObj = new Object();
            synchronized (this){
                lockObjs.add(lockObj);  // add threads lockObj to the queue
            }
            boolean mustWait = true;
            while (mustWait){
                synchronized (this){
                    mustWait = isLocked || lockObjs.get(0) != lockObj;  // let go of the first thread
                    if(!mustWait){
                        if(lockObjs.size() > 0){
                            lockObjs.remove(lockObj);
                        }
                        isLocked = true;
                        return;
                    }
                }
                synchronized (lockObj){
                    if(mustWait){
                        try {
                            lockObj.wait();  // other threads need to wait
                        } catch (InterruptedException e) {  }
                    }
                }
            }
        }
        public void unlock(){
            if(lockObjs.size() > 0){
                Object lockObj = lockObjs.get(0);
                synchronized (lockObj){
                    isLocked = false;
                    lockObj.notify();
                }
            }
        }
    }
    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        int threadCount = 3;
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_A_" + i){
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

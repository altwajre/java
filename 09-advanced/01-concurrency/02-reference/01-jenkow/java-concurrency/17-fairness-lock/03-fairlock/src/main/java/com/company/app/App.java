package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
http://tutorials.jenkov.com/java-concurrency/starvation-and-fairness.html

Solution:
Every thread calling lock() is now queued, and only the first thread in the queue is allowed to lock
the FairLock instance if it is unlocked. All other threads are parked waiting until they reach the
top of the queue.

output:
Adding element by thread runnable2
Adding element by thread runnable1
Adding element by thread runnable2
Adding element by thread runnable1
Adding element by thread runnable1
Adding element by thread runnable2
0 runnable2; 0 runnable1; 2 runnable2; 2 runnable1; 4 runnable1; 4 runnable2;

 */
public class App
{
    static int safeArrayCount = 0;
    static class ThreadSafeArrayList{
        private final FairLock lock = new FairLock();
        public final List<String> list = new ArrayList<String>();
        public void add(String o){
            lock.lock();
            try {
                safeArrayCount++;
                list.add(o);
                System.out.println("Adding element by thread " + Thread.currentThread().getName());
            }
            finally { lock.unlock(); }
        }
    }
    static class QueueObject{
        private boolean isNotified = false;
        public synchronized void doWait() throws InterruptedException {
            while(!isNotified){ this.wait(); }
            this.isNotified = false;
        }
        public synchronized void doNotify(){
            this.isNotified = true;
            this.notify();
        }
        public boolean equals(Object o){ return this == o; }
    }
    static class FairLock{
        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();
        public void lock() {
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
            synchronized (this){ waitingThreads.add(queueObject); }
            while (isLockedForThisThread){
                synchronized (this){
                    isLockedForThisThread = isLocked || waitingThreads.get(0) != queueObject;
                    if(!isLockedForThisThread){
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }
                try { queueObject.doWait(); }
                catch (InterruptedException e) {
                    synchronized (this){ waitingThreads.remove(queueObject); }
                    try { throw e; }
                    catch (InterruptedException e1) { e1.printStackTrace(); }
                }
            }
        }
        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if(waitingThreads.size() > 0){ waitingThreads.get(0).doNotify(); }
        }
    }

    public static void main( String[] args )
    {
        final ThreadSafeArrayList threadSafeArrayList = new ThreadSafeArrayList();
        Runnable runnable1 = new Runnable() {
            public void run() {
                while(safeArrayCount < 6){
                    threadSafeArrayList.add(String.valueOf(safeArrayCount) + " " + Thread.currentThread().getName());
                    try{
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            public void run() {
                while (safeArrayCount < 6){
                    threadSafeArrayList.add(String.valueOf(safeArrayCount) + " " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(runnable1, "runnable1");
        Thread thread2 = new Thread(runnable2, "runnable2");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(String s : threadSafeArrayList.list){
            System.out.print(s + "; ");
        }
    }
}

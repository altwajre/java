package com.company.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 */
public class App 
{
    static int count = 0;
    static class Counter{
        FairLock lock = new FairLock();
        public void increment(){
            lock.lock();
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("  "+Thread.currentThread().getName() + " increment finished; count: " + count);
        }
    }
    static class QueueObject{  // wait and notify signal
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
    static class FairLock{
        boolean isLocked = false;
        Thread lockingThread = null;
        Queue<QueueObject> waitingThreads = new LinkedList<QueueObject>();
        public void lock(){
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
            synchronized (this){
//                System.out.println(" "+Thread.currentThread().getName() + " offer");
                waitingThreads.offer(queueObject);
                list.add(Thread.currentThread().getName());
            }
            while(isLockedForThisThread){
//                System.out.println(" "+Thread.currentThread().getName() + " while()");
                synchronized (this){
//                    System.out.println(" "+Thread.currentThread().getName() + " synced in while()");
                    boolean isLastItemInQueue = waitingThreads.peek() != queueObject;
                    // false||false=false;  false||true=true;  true||false=true;  true||true=true
                    isLockedForThisThread = isLocked || isLastItemInQueue;
                    if(!isLockedForThisThread){
                        System.out.println(Thread.currentThread().getName() + " isLockedForThisThread=" + isLockedForThisThread
                                + " isLocked=" + isLocked + " isLastItemInQueue=" + isLastItemInQueue);
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }
                System.out.println(Thread.currentThread().getName() + " doWait");
                queueObject.doWait();
            }
        }
        public void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            if(waitingThreads.size() > 0){
                System.out.println(" "+Thread.currentThread().getName() + " doNotify");
                waitingThreads.peek().doNotify();
            }
            isLocked = false;
            lockingThread = null;
        }
    }
    public static void main( String[] args )
    {
        int threadCount = 3;
        final Counter counter = new Counter();
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);
    }
}

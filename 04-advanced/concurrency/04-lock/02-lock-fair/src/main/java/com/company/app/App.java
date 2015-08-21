package com.company.app;

import java.util.LinkedList;
import java.util.Queue;

/*
 */
public class App 
{
    static int count = 0;
    static class Counter{
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println(" "+Thread.currentThread().getName() + " increment finished; count: " + count);
        }
    }
    static class QueueObject{  // wait and notify signal
        boolean isNotified = false;
        public synchronized void doWait(){
            while(!isNotified){
                try {
                    wait();
                } catch (InterruptedException e) { }
            }
            isNotified = false;
        }
        public synchronized void doNotify(){
            notify();
            isNotified = true;
        }
    }
    static class FairLock{
        boolean isLocked = false;
        Thread lockingThread = null;
        Queue<QueueObject> waitingThreads = new LinkedList<QueueObject>();
        public void lock(){
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
            synchronized (this){
                waitingThreads.offer(queueObject);
            }
            while(isLockedForThisThread){

            }
        }
    }


    static class Lock{
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while (isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        wait();
                    } catch (InterruptedException e) { }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
                System.out.println(" "+Thread.currentThread().getName() + " notify");
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args )
    {
        int threadCount = 3;
        final Counter counter = new Counter();
        for(int i = 0; i < threadCount; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
        for(int i = 0; i < threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
    }
}

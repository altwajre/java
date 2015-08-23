package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
Problem: nested monitor lockout occurs
1, in lock(), the inner queueObject.wait() is holding the synchronization lock on "this" object,
   and it output:
Thread_A_1 wait
is waiting for queueObject.notify()
2, for unlock(), no thread can enter synchronized unlock() because "this" object is locked by lock()


 */
public class App 
{
    static int count = 0;
    static class Counter{
        Lock lock = new Lock();
        public void increment() throws InterruptedException {
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("  "+Thread.currentThread().getName() + " increment end");
        }
    }
    static class Lock{
        boolean isLocked = false;
        Thread lockingThread = null;
        List<Object> waitingThreads = new ArrayList<Object>();
        public void lock() throws InterruptedException {
            Object queueObject = new Object();

            synchronized (this){
                waitingThreads.add(queueObject);

                while(isLocked || waitingThreads.get(0) != queueObject){

                    synchronized (queueObject){
                        try {
                            System.out.println(Thread.currentThread().getName() + " wait");
                            queueObject.wait();
                        } catch (InterruptedException e) {
                            waitingThreads.remove(queueObject);
                            throw e;
                        }
                    }
                }

                waitingThreads.remove(queueObject);
                isLocked = true;
                lockingThread = Thread.currentThread();
            }
        }
        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if(waitingThreads.size() > 0){
                Object queueObject = waitingThreads.get(0);
                synchronized (queueObject){
                    System.out.println(" " + Thread.currentThread().getName() + " notify");
                    queueObject.notify();
                }
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
                    try {
                        counter.increment();
                    } catch (InterruptedException e) { }
                }
            }.start();
        }
        for(int i = 0; i < threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    try {
                        counter.increment();
                    } catch (InterruptedException e) { }
                }
            }.start();
        }
    }
}

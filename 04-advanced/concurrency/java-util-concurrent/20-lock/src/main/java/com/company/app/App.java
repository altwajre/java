package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
http://tutorials.jenkov.com/java-util-concurrent/lock.html
http://examples.javacodegeeks.com/core-java/util/concurrent/locks-concurrent/reentrantlock/java-reentrantlock-example/

ReentrantLock Class provides a constructor that optionally takes a boolean parameter fair. when more than one thread
are competing for the same luck, the lock favors granting access to the longest waiting thread.

ReentrantLock Class provides a number of ways to acquire locks. The tryLock() and the tryLock
(long timeout, TimeUnit unit) methods provide means for the threads to time-out while waiting for a lock.

output:
Adding element by thread syncThread
Adding element by thread lockingThread
Adding element by thread syncThread
Adding element by thread lockingThread
Adding element by thread syncThread
Adding element by thread lockingThread

 */
public class App 
{
    static int i = 0;
    static class ThreadSafeArrayList{
        private final Lock lock = new ReentrantLock();
        private final List<String> list = new ArrayList<String>();
        public void set(String o){
            lock.lock();
            try{
                i++;
                list.add(o);
                System.out.println("Adding element by thread " + Thread.currentThread().getName());
            }
            finally {
                lock.unlock();
            }
        }
    }
    public static void main( String[] args )
    {
        final ThreadSafeArrayList threadSafeArrayList = new ThreadSafeArrayList();
        Runnable syncThread = new Runnable() {
            public void run() {
                while(i < 6){
                    threadSafeArrayList.set(String.valueOf(i));
                    try{
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable lockingThread = new Runnable() {
            public void run() {
                while (i < 6){
                    threadSafeArrayList.set(String.valueOf(i));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread1 = new Thread(syncThread, "syncThread");
        Thread thread2 = new Thread(lockingThread, "lockingThread");
        thread1.start();
        thread2.start();
    }
}

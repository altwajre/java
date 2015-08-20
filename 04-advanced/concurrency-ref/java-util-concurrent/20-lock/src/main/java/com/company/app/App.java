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
#testThreadSafeArrayList
Adding element by thread lockingThread
Adding element by thread syncThread
Adding element by thread lockingThread
Adding element by thread syncThread
Adding element by thread syncThread
Adding element by thread lockingThread
0 lockingThread; 0 syncThread; 2 lockingThread; 2 syncThread; 4 syncThread; 5 lockingThread;

#testArrayList
0 Runnable_1; 0 Runnable_2; 1 Runnable_1; 2 Runnable_2; 3 Runnable_1; 4 Runnable_2; 5 Runnable_2;

 */
public class App 
{
    static int safeArrayCount = 0;
    static class ThreadSafeArrayList{
        private final Lock lock = new ReentrantLock();
        public final List<String> list = new ArrayList<String>();
        public void set(String o){
            lock.lock();
            try{
                safeArrayCount++;
                list.add(o);
                System.out.println("Adding element by thread " + Thread.currentThread().getName());
            }
            finally {
                lock.unlock();
            }
        }
    }
    private static void testThreadSafeArrayList() {
        System.out.println("#testThreadSafeArrayList");
        final ThreadSafeArrayList threadSafeArrayList = new ThreadSafeArrayList();
        Runnable syncThread = new Runnable() {
            public void run() {
                while(safeArrayCount < 6){
                    threadSafeArrayList.set(String.valueOf(safeArrayCount) + " " + Thread.currentThread().getName());
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
                while (safeArrayCount < 6){
                    threadSafeArrayList.set(String.valueOf(safeArrayCount) + " " + Thread.currentThread().getName());
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(String s : threadSafeArrayList.list){
            System.out.print(s + "; ");
        }
        System.out.println("\n");
    }

    public static void main( String[] args )
    {
        /*
        two threads takes turn to add stuff to arraylist which is predictable

        output:
        Adding element by thread syncThread
        Adding element by thread lockingThread
        Adding element by thread syncThread
        Adding element by thread lockingThread
        Adding element by thread syncThread
        Adding element by thread lockingThread
         */
        testThreadSafeArrayList();

        /*
        two threads randomly add stuff to arraylist which is unpredictable

        output:
        0 Runnable_1; 0 Runnable_2; 1 Runnable_1; 2 Runnable_2; 3 Runnable_1; 4 Runnable_2; 5 Runnable_1;
         */
        testArrayList();
    }

    static int count = 0;
    private static void testArrayList() {
        System.out.println("#testArrayList");
        final List<String> list = new ArrayList<String>();
        Runnable runnable1 = new Runnable() {
            public void run() {
                while(count < 6){
                    list.add(String.valueOf(count) + " " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            public void run() {
                while(count < 6){
                    list.add(String.valueOf(count) + " " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        };
        Thread thread1 = new Thread(runnable1, "Runnable_1");
        Thread thread2 = new Thread(runnable2, "Runnable_2");
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(String item : list){
            System.out.print(item + "; ");
        }
    }
}

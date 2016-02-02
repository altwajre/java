package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
http://tutorials.jenkov.com/java-util-concurrent/readwritelock.html

It allows multiple threads to read a certain resource, but only one to write it at a time.
Multiple threads can read from a shared resource without causing concurrency errors. The concurrency errors first occur
when reads and writes to a shared resource occur concurrently, or if multiple writes take place concurrently.

ReadWriteLock Locking Rules
Read Lock
  If no thread have locked the ReadWriteLock for writing, and no thread have requested a write lock. Thus, multiple
  threads can lock the lock the reading.
Write Lock
  If no thread are reading or writing. Thus, only one thread at time can lock the lock for writing.
 */
class ThreadSafeArrayList<T> {
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();
    List<T> list = new ArrayList<>();

    public void add(T t) {
        writeLock.lock();
        try {
            list.add(t);
            System.out.println(Thread.currentThread().getName() + " after writeLock.lock() - add " + t);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int i) {
        readLock.lock();
        try {
            T t = list.get(i);
            System.out.println("  " + Thread.currentThread().getName() + " after readLock.lock() - get(" + i + ")=" + t);
            return t;
        } finally {
            readLock.unlock();
        }
    }
}

public class App {
    public static void main(String[] args) {
        final ThreadSafeArrayList<Integer> threadSafeArrayList = new ThreadSafeArrayList<Integer>();
        new Thread("Thread_1") {
            public void run() {
                threadSafeArrayList.add(1);
            }
        }.start();

        new Thread("Thread_2") {
            public void run() {
                threadSafeArrayList.add(2);
            }
        }.start();

        new Thread("Thread_3") {
            public void run() {
                threadSafeArrayList.get(1);
            }
        }.start();

        new Thread("Thread_4") {
            public void run() {
                threadSafeArrayList.add(3);
            }
        }.start();

        new Thread("Thread_5") {
            public void run() {
                threadSafeArrayList.get(0);
            }
        }.start();
    }
}
/*
output:
Thread_1 after writeLock.lock() - add 1
Thread_2 after writeLock.lock() - add 2
  Thread_3 after readLock.lock() - get(1)=2
Thread_4 after writeLock.lock() - add 3
  Thread_5 after readLock.lock() - get(0)=1
 */

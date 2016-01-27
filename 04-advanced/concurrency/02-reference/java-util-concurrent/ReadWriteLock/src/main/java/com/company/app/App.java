package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*

output:
Thread_1 after writeLock.lock() - add 1
Thread_2 after writeLock.lock() - add 2
  Thread_3 after readLock.lock() - get 1
Thread_4 after writeLock.lock() - add 3
  Thread_5 after readLock.lock() - get 0

 */
public class App 
{
    static class ThreadSafeArrayList<T>{
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        List<T> list = new ArrayList<T>();
        public void add(T t){
            writeLock.lock();
            try{
                list.add(t);
                System.out.println(Thread.currentThread().getName() + " after writeLock.lock() - add " + t);
            }
            finally {
                writeLock.unlock();
            }
        }
        public T get(int i){
            readLock.lock();
            try{
                System.out.println("  " + Thread.currentThread().getName() + " after readLock.lock() - get " + i );
                return list.get(i);
            }
            finally {
                readLock.unlock();
            }
        }
    }
    public static void main( String[] args )
    {
        final ThreadSafeArrayList<Integer> threadSafeArrayList = new ThreadSafeArrayList<Integer>();
        new Thread("Thread_1"){
            public void run(){
                threadSafeArrayList.add(1);
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                threadSafeArrayList.add(2);
            }
        }.start();

        new Thread("Thread_3"){
            public void run(){
                threadSafeArrayList.get(1);
            }
        }.start();

        new Thread("Thread_4"){
            public void run(){
                threadSafeArrayList.add(3);
            }
        }.start();

        new Thread("Thread_5"){
            public void run(){
                threadSafeArrayList.get(0);
            }
        }.start();
    }
}

package com.company.app;

import java.util.*;

/*
For how to implement the FairLock, see Slipped Conditions realistic example at link below
http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html

output:
Thread_A_3 doWait
Thread_B_3 doWait
Thread_A_2 doWait
Thread_B_2 doWait
Thread_B_1 doWait
 Thread_A_1 doNotify
  Thread_A_1 increment finished; count: 1
 Thread_A_3 doNotify
  Thread_A_3 increment finished; count: 2
 Thread_B_1 doNotify
  Thread_B_1 increment finished; count: 3
 Thread_B_2 doNotify
  Thread_B_2 increment finished; count: 4
 Thread_A_2 doNotify
  Thread_A_2 increment finished; count: 5
  Thread_B_3 increment finished; count: 6
[Thread_A_1, Thread_A_3, Thread_B_1, Thread_B_2, Thread_A_2, Thread_B_3]

 */
public class App 
{
    static int count = 0;
    static class Counter{
        FairLock lock = new FairLock();
        public void increment() throws InterruptedException {
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
    static class FairLock {
        boolean isLocked = false;
        Set<String> isLockedList = new HashSet<String>();
        Thread lockingThread = null;
        List<QueueObject> workerThreads = new ArrayList<QueueObject>();
        public void lock() throws InterruptedException {
            QueueObject queueObject = new QueueObject();  // use wait and notify to avoid missed signal
            synchronized (this){
                list.add(Thread.currentThread().getName());
                workerThreads.add(queueObject);
            }
            boolean mustWait = true;
            while(mustWait){
                synchronized (this){
                    isLockedList.add(Thread.currentThread().getName() + " isLocked=" + isLocked);
                    /*   must wait when following
                    1, isLocked=true or
                    2, queueObject is not the first worker thread which means (workerThreads.get(0) != queueObject)=true
                     */
                    mustWait = isLocked || workerThreads.get(0) != queueObject;
                    /*  if !mustWait which means we do not need to wait, so a thread can go in to do the work
                    1, remove queueObject from workerThreads
                    2, set isLocked to true
                    3, set lockingThread to currentThread
                     */
                    if(!mustWait){  // when mustWait is false,
                        if(workerThreads.size() > 0) workerThreads.remove(queueObject);
                        isLocked = true;
                        lockingThread = Thread.currentThread();
                        return; // no reason to continue when mustWait=false
                    }
                }
                /*
                1, lock the queueObject
                2, invoke wait() if mustWait=true
                 */
                synchronized (queueObject){
                    if(mustWait){
                        System.out.println(Thread.currentThread().getName() + " doWait");
                        queueObject.doWait();
                    }
                }
            }
        }
        public synchronized void unlock(){
            if(this.lockingThread != Thread.currentThread()){
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if(workerThreads.size() > 0){
                QueueObject queueObject = workerThreads.get(0);
                synchronized (queueObject){
                    System.out.println(" " + Thread.currentThread().getName() + " doNotify");
                    queueObject.doNotify();
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
                    try {
                        counter.increment();
                    } catch (InterruptedException e) { }
                }
            }.start();
        }
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    try {
                        counter.increment();
                    } catch (InterruptedException e) { }
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

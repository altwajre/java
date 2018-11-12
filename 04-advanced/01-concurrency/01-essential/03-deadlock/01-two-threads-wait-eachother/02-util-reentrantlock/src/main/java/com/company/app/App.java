package com.company.app;

import java.util.concurrent.locks.ReentrantLock;

/*
Thread 1 locks A, waits for B
Thread 2 locks B, waits for A

output:
Thread_1 locked lock_A
Thread_2 locked lock_B
Thread_2 try to lock lock_A
Thread_1 try to lock lock_B

 */
public class App 
{
    public static void main( String[] args )
    {
        final ReentrantLock lock_A = new ReentrantLock();
        final ReentrantLock lock_B = new ReentrantLock();

        new Thread("Thread_1"){
            public void run(){
                String threadName = Thread.currentThread().getName();
                lock_A.lock();
                System.out.println(threadName + " locked lock_A");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " try to lock lock_B");
                lock_B.lock();
                System.out.println(threadName + " locked lock_B");

                lock_B.unlock();
                lock_A.unlock();
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                String threadName = Thread.currentThread().getName();
                lock_B.lock();
                System.out.println(threadName + " locked lock_B");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " try to lock lock_A");
                lock_A.lock();
                System.out.println(threadName + "locked lock_A");

                lock_A.unlock();
                lock_B.unlock();
            }
        }.start();
    }
}

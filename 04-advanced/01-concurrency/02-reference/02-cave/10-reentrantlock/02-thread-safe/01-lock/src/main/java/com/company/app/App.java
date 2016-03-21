package com.company.app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Thread Safe after using lock

https://www.youtube.com/watch?v=fjMTaVykOpc&list=PLBB24CFB073F1048E&index=10

It works because only one thread can get the lock at a time.
 */
class Runner{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private void increment(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }
    public void firstThread(){
        lock.lock();
        try{
            increment();
        }
        finally {
            lock.unlock();
        }
    }
    public void secondThread(){
        lock.lock();
        try{
            increment();
        }
        finally {
            lock.unlock();
        }
    }
    public void finished(){
        System.out.println("Count is: " + count);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.finished();
    }
}
/*
output:
Count is: 20000
 */

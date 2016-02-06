package com.company.app;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Thread Safe

https://www.youtube.com/watch?v=fjMTaVykOpc&list=PLBB24CFB073F1048E&index=10

Scenario:
We can use lock condition to wait to signal between threads
 */
class Runner{
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private void increment(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }
    public void firstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await(); // wait and release the lock, so other thread can get the lock
        System.out.println("Woken up!");
        try{
            increment();
        }
        finally {
            lock.unlock();
        }
    }
    public void secondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        condition.signal(); // wake the await() in the other thread.

        try{
            increment();
        }
        finally {
            lock.unlock(); // release the lock, so other thread can get the lock
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
                try {
                    runner.firstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    runner.secondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
Waiting...
Press the return key!

Got return key!
Woken up!
Count is: 20000
 */

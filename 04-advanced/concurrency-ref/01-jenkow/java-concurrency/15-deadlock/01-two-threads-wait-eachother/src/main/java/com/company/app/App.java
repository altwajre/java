package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/deadlock.html

Thread 1 locks A, waits for B
Thread 2 locks B, waits for A

output:
pool-1-thread-1 locked lock_A
pool-1-thread-2 locked lock_B
pool-1-thread-1 try to lock lock_B
pool-1-thread-2 try to lock lock_A
pool-1-thread-1 lock_B.wait()
pool-1-thread-2 lock_A.wait()

 */
public class App
{
    static class Lock{
        public Lock(String name){ this.name = name; }
        public String name;
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + name + ".wait()");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " " + name + ".notify()");
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args) {
        Lock lock_A = new Lock("lock_A");
        Lock lock_B = new Lock("lock_B");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            lock_A.lock();
            System.out.println(threadName + " locked " + lock_A.name);
            sleep(800);
            System.out.println(threadName + " try to lock " + lock_B.name);
            lock_B.lock();
            System.out.println(threadName + " locked " + lock_B.name);

            lock_B.unlock();
            lock_A.unlock();
        });
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            lock_B.lock();
            System.out.println(threadName + " locked " + lock_B.name);
            sleep(800);
            System.out.println(threadName + " try to lock " + lock_A.name);
            lock_A.lock();
            System.out.println(threadName + " locked " + lock_A.name);

            lock_A.unlock();
            lock_B.unlock();
        });
    }
    static void sleep(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

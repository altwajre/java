package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/anatomy-of-a-synchronizer.html#notificationstrategy

Notification Strategy
Notify 1 random of N waiting threads
The notifying thread call notify() on the object the waiting threads have called wait() on. Calling notify
makes no guarantee about which of the waiting threads will be notified.

output:
pool-1-thread-1; exit lock
pool-1-thread-5; wait
pool-1-thread-4; wait
pool-1-thread-3; wait
pool-1-thread-2; wait
attempt to shutdown executor
unlock_Thread; exit unlock
pool-1-thread-5; EXIT wait
pool-1-thread-5; exit lock

 */
public class App 
{
    static class Lock{
        private Object monitorObject = new Object();
        private boolean isLocked = false;
        public void lock(){
            synchronized (monitorObject){
                while(isLocked){
                    System.out.println(Thread.currentThread().getName() + "; wait");
                    try {
                        monitorObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "; EXIT wait");
                }
                isLocked = true;
                System.out.println(Thread.currentThread().getName() + "; exit lock");
            }
        }
        public void unlock(){
            synchronized (monitorObject){
                isLocked = false;
                monitorObject.notify();
                System.out.println(Thread.currentThread().getName() + "; exit unlock");
            }
        }
    }
    public static void main( String[] args )
    {
        Lock lock = new Lock();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable lockTask = () -> {
            lock.lock();
        };
        for(int i = 1; i <= 5; i++){
            executor.submit(lockTask);
        }

        new Thread("unlock_Thread"){
            public void run(){
                lock.unlock();
            }
        }.start();

        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e){
            System.err.println("tasks interrupted");
        }
        finally {
            if(!executor.isTerminated()){
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}

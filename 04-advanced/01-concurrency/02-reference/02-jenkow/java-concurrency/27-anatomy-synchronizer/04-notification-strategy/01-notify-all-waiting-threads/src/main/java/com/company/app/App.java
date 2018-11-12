package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/anatomy-of-a-synchronizer.html#notificationstrategy

Notification Strategy
Notify all waiting threads
All waiting threads call wait() on the same object. Once a thread calls notifyAll() on the object the waiting threads
called wait() on will wake these waiting threads.

output:
pool-1-thread-1; exit lock
pool-1-thread-3; wait
pool-1-thread-2; wait
attempt to shutdown executor
unlockAll_Thread; exit unlockAll
pool-1-thread-2; EXIT wait
pool-1-thread-2; exit lock
pool-1-thread-3; EXIT wait
pool-1-thread-3; wait

 */
public class App 
{
    static class Lock{
        private Object monitorObject = new Object();
        private boolean isLocked = false;
        public void lock() {
            synchronized (monitorObject){
                while (isLocked){
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
        public void unlockAll(){
            synchronized (monitorObject){
                isLocked = false;
                monitorObject.notifyAll();
                System.out.println(Thread.currentThread().getName() + "; exit unlockAll");
            }
        }
    }
    public static void main( String[] args )
    {
        Lock lock = new Lock();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable lockTask = () -> {
            lock.lock();
        };
        for(int i = 1; i <= 3; i++){
            executor.submit(lockTask);
        }

        new Thread("unlockAll_Thread"){
            public void run(){
                lock.unlockAll();
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

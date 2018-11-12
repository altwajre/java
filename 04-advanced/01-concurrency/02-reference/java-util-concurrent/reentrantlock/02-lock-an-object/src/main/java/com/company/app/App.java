package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
lock an object such as Chopstick

output:
pool-1-thread-1: Chopstick.pickUp()
pool-1-thread-2: Lock.lock() wait()
attempt to shutdown executor
pool-1-thread-3: Lock.lock() wait()
shutdown finished
cancel non-finished tasks
pool-1-thread-3: Lock.lock() wait()
pool-1-thread-2: Lock.lock() wait()

 */
public class App 
{
    static class Chopstick{
        public Lock lock = new Lock();
        public void pickUp(){
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": Chopstick.pickUp()");
        }
        public void putDown(){
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ": Chopstick.putDown()");
        }
    }
    static class Lock{
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + ": Lock.lock() wait()");
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
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args )
    {
        Chopstick chopstick = new Chopstick();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(()->{
            chopstick.pickUp();
        });
        executor.submit(()->{
            chopstick.pickUp();
        });
        executor.submit(()->{
            chopstick.pickUp();
        });

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

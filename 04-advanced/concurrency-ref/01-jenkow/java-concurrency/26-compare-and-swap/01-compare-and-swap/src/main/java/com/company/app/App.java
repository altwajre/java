package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/compare-and-swap.html

To work properly in a multithreaded application, "check then act" operations must be atomic which means
both the "check" and "act" actions are executed as an atomic (non-dividable) block of code. Any thread that
starts executing the block will finish executing the block without interference from other threads. No other
threads can execute the atomic block at the same time.

output:
pool-1-thread-1; locked=false
attempt to shutdown executor
pool-1-thread-3; locked=true
pool-1-thread-2; locked=true
shutdown finished

 */
public class App 
{
    static class MyLock{
        private boolean locked = false;
        public synchronized boolean lock(){
            System.out.println(Thread.currentThread().getName() + "; locked=" + locked);
            if(!locked){  // check
                locked = true;  // then act
                return true;
            }
            return false;
        }
    }
    public static void main( String[] args )
    {
        MyLock lock = new MyLock();

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            lock.lock();
        };

        executor.submit(task);
        executor.submit(task);
        executor.submit(task);

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

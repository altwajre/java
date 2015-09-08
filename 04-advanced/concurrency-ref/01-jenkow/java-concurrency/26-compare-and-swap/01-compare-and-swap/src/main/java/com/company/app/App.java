package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/compare-and-swap.html

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
            if(!locked){
                locked = true;
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

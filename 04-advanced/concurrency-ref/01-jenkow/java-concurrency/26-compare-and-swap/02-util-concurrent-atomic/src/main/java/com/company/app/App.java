package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*
http://tutorials.jenkov.com/java-concurrency/compare-and-swap.html

compareAndSet() will compare the value of the AtomicBoolean instance to an expected value, and if it has the
expected value, it swaps the value with a new value.

In this case, it compares the value of locked to false and if it is false, it sets the new value of the
AtomicBoolean to true.

The compareAndSet() returns true if the value was swapped, and false if not.

output:
true
false
attempt to shutdown executor
false
shutdown finished

 */
public class App 
{
    static class MyLock{
        private AtomicBoolean locked = new AtomicBoolean(false);
        public boolean lock(){
            return locked.compareAndSet(false, true);
        }
    }
    public static void main( String[] args )
    {
        MyLock lock = new MyLock();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable task = () -> {
            System.out.println(lock.lock());
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

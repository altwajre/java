package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/non-blocking-algorithms.html

** blocking algorithm

If need more than one thread to write to the same, shared variable, a volatile variable will not be sufficient.
You will need some kind of exclusive access to the variable.

Problem:
It is a blocking algorithm because notice how the inc() and count() methods both contain a synchronized block.
This is what we want to avoid - synchronized blocks and wait() - notify() calls etc.

output:
attempt to shutdown executor
Count=4
shutdown finished

 */
public class App 
{
    static class SynchronizedCounter{
        long count = 0;
        public void inc(){
            synchronized (this){
                count++;
            }
        }
        public long getCount(){
            synchronized (this){
                return this.count;
            }
        }
    }
    public static void main( String[] args )
    {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 4; i++){
            executor.submit(() -> {
                synchronizedCounter.inc();
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.submit(() -> {
            System.out.println("Count="+synchronizedCounter.getCount());
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
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

package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*

lock and unlock a critical section in a method

output:
counter.lock.isFair()=false
attempt to shutdown executor
shutdown finished
3

 */
public class App 
{
    static class Counter{
        public int count = 0;
        public ReentrantLock lock = new ReentrantLock();
        public void increment(){
            lock.lock();
            count++; // critical section
            lock.unlock();
        }
    }
    public static void main( String[] args )
    {
        Counter counter = new Counter();
        System.out.println("counter.lock.isFair()=" + counter.lock.isFair());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executor.submit(() -> {
                counter.increment();
            });
        }
        stop(executor);
        System.out.println(counter.count);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
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

package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*
http://tutorials.jenkov.com/java-util-concurrent/lock.html

lock and unlock a critical section in a method

 */
class Counter{
    public int count = 0;
    public ReentrantLock lock = new ReentrantLock();
    public void increment(){
        lock.lock();
        count++; // critical section
        lock.unlock();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Counter counter = new Counter();
        System.out.println("counter.lock.isFair()=" + counter.lock.isFair());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            executorService.submit(() -> {
                counter.increment();
            });
        }
        stop(executorService);
        System.out.println("counter.count="+counter.count);
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
/*
output:
counter.lock.isFair()=false
attempt to shutdown executor
shutdown finished
counter.count=5
 */
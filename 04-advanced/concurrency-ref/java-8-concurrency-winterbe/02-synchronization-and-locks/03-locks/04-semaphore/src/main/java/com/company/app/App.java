package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

This is useful in different scenarios where you have to limit the amount concurrent access to certain
parts of your application.

The example blow:
The executor can potentially run 10 tasks concurrently but we use a semaphore of size 5, thus limiting concurrent
access to 5. It is important to use a try/finally block to properly release the semaphore even in case of exceptions.
The semaphores permits access to the actual long running operation simulated by sleep(5) up to a maximum of 5.
Every subsequent call to tryAcquire() elapses the maximum wait time out of one second, resulting in the appropriate
console output that no semaphore could be acquired.

output:
pool-1-thread-1: Semaphore acquired
pool-1-thread-5: Semaphore acquired
pool-1-thread-4: Semaphore acquired
pool-1-thread-3: Semaphore acquired
pool-1-thread-2: Semaphore acquired
attempt to shutdown executor
pool-1-thread-8: Could not acquire semaphore
pool-1-thread-10: Could not acquire semaphore
pool-1-thread-6: Could not acquire semaphore
pool-1-thread-7: Could not acquire semaphore
pool-1-thread-9: Could not acquire semaphore
shutdown finished

 */
public class App 
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);
        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                int timeout = 1;
                permit = semaphore.tryAcquire(timeout, TimeUnit.SECONDS);
                if(permit){
                    System.out.println(Thread.currentThread().getName() + ": Semaphore acquired");
                    sleep(3);
                }
                else{
                    System.out.println(Thread.currentThread().getName() + ": Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(permit){
                    semaphore.release();
                }
            }
        };
        IntStream.range(0, 10)
                .forEach(i -> executor.submit(longRunningTask));
        stop(executor);
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
    static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

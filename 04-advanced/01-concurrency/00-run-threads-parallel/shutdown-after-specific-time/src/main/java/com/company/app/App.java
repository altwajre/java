package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep 1 seconds");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) { }
            System.out.println(threadName + ": after sleep 1 seconds");
        });

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep 2 seconds");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) { }
            System.out.println(threadName + ": after sleep 2 seconds");
        });

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep 10 seconds");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) { }
            System.out.println(threadName + ": after sleep 10 seconds");
        });

        stop(executor);
    }

    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(4, TimeUnit.SECONDS);
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
pool-1-thread-1: before sleep 1 seconds
pool-1-thread-2: before sleep 2 seconds
pool-1-thread-3: before sleep 10 seconds
attempt to shutdown executor
pool-1-thread-1: after sleep 1 seconds
pool-1-thread-2: after sleep 2 seconds
shutdown finished
pool-1-thread-3: after sleep 10 seconds
cancel non-finished tasks
 */

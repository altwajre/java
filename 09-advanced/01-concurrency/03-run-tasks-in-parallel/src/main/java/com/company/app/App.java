package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
        Runnable task = () -> System.out.println("Hello " + Thread.currentThread().getName());

        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++){
            executor.submit(task);
        }

        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(3, TimeUnit.SECONDS);
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
Hello pool-1-thread-1
Hello pool-1-thread-5
attempt to shutdown executor
Hello pool-1-thread-4
Hello pool-1-thread-3
Hello pool-1-thread-2
shutdown finished
 */

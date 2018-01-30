package com.company.app;

import java.util.concurrent.*;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

Executors are capable of running asynchronous tasks any typically manage a pool of threads, so we don't have to
create new threads manually.
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep 2 seconds");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(threadName + ": after sleep 2 seconds");
        });

        try {
            System.out.println("future: "+future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

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
}
/*
output:
attempt to shutdown executor
pool-1-thread-1: before sleep 2 seconds
pool-1-thread-1: after sleep 2 seconds
shutdown finished
 */

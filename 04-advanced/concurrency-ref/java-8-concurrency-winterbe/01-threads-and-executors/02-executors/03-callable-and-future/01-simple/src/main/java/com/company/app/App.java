package com.company.app;

import java.util.concurrent.*;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

1, callable returns a value instead of void like runnable
2, future will retrieve the return value from callable
  - isDone() check if the future has already finished the execution
  - get() blocks the current thread and waits until the callable completes before returning the actual result
3, future is tightly coupled to the underlying executor service. the non-terminated future will
   throw exception executor is shutdown

output:
future done? false
future done? true
result: 123
attempt to shutdown executor
shutdown finished
 */
public class App 
{
    public static void main( String[] args )
    {
        Callable<Integer> task = () -> {
            try{
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);
        System.out.println("future done? " + future.isDone());
        Integer result = -1;
        try {
            result = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);

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

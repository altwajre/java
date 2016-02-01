package com.company.app;

import java.util.concurrent.*;

/*
http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html

It is an ExecutorService which can schedule tasks to run after a delay, or to execute repeatedly with a fixed interval
of time in between each execution.
 */
public class App
{
    public static void main( String[] args )
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture future = scheduledExecutorService.schedule(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Executed!");
                return "Called";
            }
        }, 2, TimeUnit.SECONDS);
        try {
            System.out.println("result="+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }scheduledExecutorService.shutdown();
    }
}
/*
output:
Executed!
result=Called
 */

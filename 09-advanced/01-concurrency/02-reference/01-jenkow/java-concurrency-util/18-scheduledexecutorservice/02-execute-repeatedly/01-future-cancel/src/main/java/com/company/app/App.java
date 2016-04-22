package com.company.app;

import java.util.concurrent.*;

/*
http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html

It is an ExecutorService which can schedule tasks to run after a delay, or to execute repeatedly with a fixed interval
of time in between each execution.
 */
public class App
{
    static int count = 1;
    public static void main( String[] args )
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture future = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " executed_"+count++);
            }
        }, 1, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        future.cancel(false);
        scheduledExecutorService.shutdown();
    }
}
/*
output:
pool-1-thread-1 executed_1
pool-1-thread-1 executed_2
pool-1-thread-2 executed_3
 */
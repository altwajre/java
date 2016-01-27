package com.company.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

Schedules a task to run after an initial delay of three seconds has passed

output:
423526373727089
Remaining Delay: 1995ms
Scheduling: 423529433549816

 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println(System.nanoTime());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // System.nanoTime() returns the current value of the most precise available system timer.
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());  // will run after delay
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);  // delay three seconds
        TimeUnit.SECONDS.sleep(1);
        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);  // get remaining delay
        System.out.printf("Remaining Delay: %sms\n", remainingDelay);
    }
}

package com.company.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

executing tasks (with a fixed time rate) once every second.

Problem:
scheduleAtFixedRate() doesn't take into account the actual duration of the task. So if you specify a period
of one second but the task needs 2 seconds to be executed then the thread pool will working to capacity very soon.

Solution:
Use scheduleWithFixedDelay() that the wait time period applies between the end of a task and the start of the next task.

output:
Scheduling: 424146057024313
Scheduling: 424147061526322
Scheduling: 424148064388863
Scheduling: 424149059213650
Scheduling: 424150059635390
Scheduling: 424151059951283

 */
public class App 
{
    public static void main( String[] args )
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 1;
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }
}

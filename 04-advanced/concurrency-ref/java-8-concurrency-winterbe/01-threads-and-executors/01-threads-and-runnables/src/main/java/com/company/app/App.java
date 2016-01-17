package com.company.app;

import java.util.concurrent.TimeUnit;

/*
http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 */
public class App 
{
    public static void main( String[] args )
    {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": before sleep");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + ": after sleep");
        };

        task.run();  // Run task directly

        Thread thread = new Thread(task);  // Run task in a Thread
        thread.start();

        System.out.println("Done!");
    }
}
/*
output:
main: before sleep
main: after sleep
Done!
Thread-0: before sleep
Thread-0: after sleep
 */

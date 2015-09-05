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
        task.run();

        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }
}

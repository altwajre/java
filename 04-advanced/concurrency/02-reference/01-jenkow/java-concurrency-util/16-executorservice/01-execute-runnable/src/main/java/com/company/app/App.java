package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
http://tutorials.jenkov.com/java-util-concurrent/executorservice.html

The execute(Runnable) method takes a java.lang.Runnable object, and executes it asynchronously.
There is no way of obtaining the result of the executed Runnable. You will have to use a Callable to obtain the result.
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        executorService.shutdown();
    }
}
/*
output:
Asynchronous task
 */
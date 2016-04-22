package com.company.app;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
http://tutorials.jenkov.com/java-util-concurrent/executorservice.html

The submit(Runnable) method also takes a Runnable implementation, but returns a Future Object. This Future object can be
used to check if the Runnable as finished executing.
 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        try {
            System.out.println("future.get()="+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
/*
output:
Asynchronous task
future.get()=null
 */

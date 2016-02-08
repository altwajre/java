package com.company.app;

import java.util.Random;
import java.util.concurrent.*;

/*
https://www.youtube.com/watch?v=lnbWFV4b7M4&list=PLBB24CFB073F1048E&index=13

Callable interface and Future
A callable thread can return a value to Future, and future.get() to get the returned value.

 */
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Integer> future = executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(2000);

                System.out.println("Starting ...");

                Thread.sleep(duration);

                System.out.println("Finished.");

                return duration;
            }
        });

        executor.shutdown();

        try {
            System.out.println("Result is: " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
Starting ...
Finished.
Result is: 775
 */

package com.company.app;

import java.util.Random;
import java.util.concurrent.*;

/*
https://www.youtube.com/watch?v=6HydEu75iQI&list=PLBB24CFB073F1048E&index=14

use future.cancel(true) to interrupt thread
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Starting...");
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Random random = new Random();
                for(int i = 0; i < 1E8; i++){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
                return null;
            }
        });

        executor.shutdown();
        Thread.sleep(500);
        future.cancel(true); // interrupt thread
        executor.awaitTermination(2, TimeUnit.MINUTES);
        System.out.println("Finished.");
    }
}
/*
output:
Starting...
Interrupted!
Finished.
 */

package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
https://www.youtube.com/watch?v=KUdro0G1BV4&list=PLBB24CFB073F1048E&index=5

Example - two workers in threadpool to process tasks (Runnable)
 */
class Processor implements Runnable{
    private int id;
    public Processor(int id){
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("Starting: " + id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}
public class App
{
    public static void main( String[] args )
    {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 5; i++){
            executorService.submit(new Processor(i));
        }

        executorService.shutdown(); // wait for all tasks completed
        System.out.println("All tasks submitted.");
        try {
            executorService.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");
    }
}
/*
output:
Starting: 0
All tasks submitted.
Starting: 1
Completed: 1
Completed: 0
Starting: 2
Starting: 3
Completed: 3
Completed: 2
Starting: 4
Completed: 4
All tasks completed.
 */
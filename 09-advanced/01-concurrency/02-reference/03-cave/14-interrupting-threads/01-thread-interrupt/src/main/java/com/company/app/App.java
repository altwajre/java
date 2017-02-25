package com.company.app;

import java.util.Random;

/*
https://www.youtube.com/watch?v=6HydEu75iQI&list=PLBB24CFB073F1048E&index=14

Thread.interrupt() sets the interrupted status/flag of the target thread. Then code running in that target thread may
poll the interrupted status and handle it appropriately.
 */
public class App
{
    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Starting...");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for(int i = 0; i < 1E8; i++){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }
                    Math.sin(random.nextDouble());
                }
            }
        });
        t.start();
        Thread.sleep(500);
        t.interrupt();
        t.join();
        System.out.println("Finished.");
    }
}
/*
output:
Starting...
Interrupted!
Finished.
 */

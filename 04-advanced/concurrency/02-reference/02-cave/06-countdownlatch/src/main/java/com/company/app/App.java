package com.company.app;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
https://www.youtube.com/watch?v=1H-Vfu1v_2g&list=PLBB24CFB073F1048E&index=6

It allows one or more threads to wait for a given set of operations to complete.
 */
class Processor implements Runnable{
    private CountDownLatch latch;
    public Processor(CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started countdown.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
public class App
{
    public static void main( String[] args )
    {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 3; i++){
            executor.submit(new Processor(latch));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Completed.");
    }
}
/*
output:
pool-1-thread-1 started countdown.
pool-1-thread-3 started countdown.
pool-1-thread-2 started countdown.
Completed.
 */

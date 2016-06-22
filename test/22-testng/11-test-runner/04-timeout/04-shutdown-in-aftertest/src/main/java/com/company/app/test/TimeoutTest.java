package com.company.app.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
    private CountDownLatch latch;
    private long sleepDuration;
    public Processor(CountDownLatch latch, long sleepDuration){
        this.latch = latch;
        this.sleepDuration = sleepDuration;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started countdown.");
        try {
            Thread.sleep(sleepDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}

public class TimeoutTest {

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    @AfterTest
    public void afterTest() {
        executor.shutdown();
    }

    @Test(timeOut = 500)
    public void expiredTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);;
        long sleepDuration = 1000;
        executor.submit(new Processor(latch, sleepDuration));

        latch.await();

        System.out.println("expiredTest should not complete");
    }

    @Test(timeOut = 500)
    public void validTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        long sleepDuration = 400;
        executor.submit(new Processor(latch, sleepDuration));

        latch.await();

        System.out.println("validTest completed");
    }
}

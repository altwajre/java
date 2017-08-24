package com.company.app.test;

import org.testng.annotations.Test;

public class TimeoutTest {
    @Test
    public void expiredTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Time test method one");
    }

    @Test
    public void validTest() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("Time test method two");
    }
}

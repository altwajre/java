package com.company.app.test;

import org.testng.annotations.Test;

public class TimeoutTest {
    @Test(timeOut = 500)
    public void expiredTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("expiredTest completed");
    }

    @Test(timeOut = 500)
    public void validTest() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("validTest completed");
    }
}

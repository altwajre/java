package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GlobalTimeoutTest {
  public static String log;
  private final CountDownLatch latch = new CountDownLatch(1);

  @Rule
  public Timeout globalTimeout = Timeout.millis(800); // 10 seconds max per method tested

  @Test
  public void testSleepForTooLong() throws InterruptedException {
    log += "ran_1";
    TimeUnit.MILLISECONDS.sleep(1000);
  }

  @Test
  public void testBlockForever() throws InterruptedException {
    log += "ran_2";
    latch.await(); // will block
  }

}

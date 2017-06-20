package com.company.app;

import org.junit.Test;

public class TimeoutTest {

  @Test(timeout = 800)
  public void testWithTimeout() throws InterruptedException {
    Thread.sleep(1000);
  }
}

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class TimeoutAttributeTest {

  @Test(timeout = 800l)
  public void testTimeout(TestContext context) {

    Async async = context.async();

    Vertx vertx = Vertx.vertx();
    vertx.setTimer(1800, v -> {

      // This handler will get called once after a specified delay
      System.out.println(Thread.currentThread().getName() + ": Timer fired");

      async.complete();
    });

  }
/*
java.util.concurrent.TimeoutException
	at io.vertx.ext.unit.impl.TestContextImpl$Step.lambda$run$0(TestContextImpl.java:112)
	at io.vertx.ext.unit.impl.TestContextImpl$Step$$Lambda$3/1330106945.run(Unknown Source)
	at java.lang.Thread.run(Thread.java:745)
 */

  @Test(timeout = 1800l)
  public void testNoTimeout(TestContext context) {

    Async async = context.async();

    Vertx vertx = Vertx.vertx();
    vertx.setTimer(800, v -> {

      // This handler will get called once after a specified delay
      System.out.println(Thread.currentThread().getName() + ": Timer fired");

      vertx.close();
      async.complete();
    });
  }
/*
vert.x-eventloop-thread-0: Timer fired
 */

}

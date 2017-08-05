package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

/*
NOTE
the @Test timeout overrides the the Timeout rule.
 */
@RunWith(VertxUnitRunner.class)
public class TimeoutRuleTest {
  @Rule
  public Timeout rule = Timeout.seconds(1);

  @Test
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
org.junit.runners.model.TestTimedOutException: test timed out after 1 seconds

	at sun.misc.Unsafe.park(Native Method)
	at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
	at java.util.concurrent.CompletableFuture$Signaller.block(CompletableFuture.java:1685)
	at java.util.concurrent.ForkJoinPool.managedBlock(ForkJoinPool.java:3320)
	at java.util.concurrent.CompletableFuture.waitingGet(CompletableFuture.java:1721)
	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1887)
	at io.vertx.ext.unit.junit.VertxUnitRunner.invokeExplosively(VertxUnitRunner.java:134)
	at io.vertx.ext.unit.junit.VertxUnitRunner.access$000(VertxUnitRunner.java:39)
	at io.vertx.ext.unit.junit.VertxUnitRunner$1.evaluate(VertxUnitRunner.java:84)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:298)
	at org.junit.internal.runners.statements.FailOnTimeout$CallableStatement.call(FailOnTimeout.java:292)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.lang.Thread.run(Thread.java:745)
 */

  @Test
  public void testNoTimeout(TestContext context) {

    Async async = context.async();

    Vertx vertx = Vertx.vertx();
    vertx.setTimer(500, v -> {

      // This handler will get called once after a specified delay
      System.out.println(Thread.currentThread().getName() + ": Timer fired");

      async.complete();
    });
  }
/*
vert.x-eventloop-thread-0: Timer fired
 */
}

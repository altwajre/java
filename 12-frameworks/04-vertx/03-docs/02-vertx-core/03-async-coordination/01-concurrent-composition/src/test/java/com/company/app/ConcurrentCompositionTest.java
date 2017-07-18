package com.company.app;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import org.junit.Test;

/*
CompositeFuture.all() takes several futures arguments (up to 6) and returns a future that is succeeded
when all the futures are and failed when at least one of the futures is failed:
 */
public class ConcurrentCompositionTest {

  @Test
  public void testAllFuturesPassed() {

    Future<Void> f1 = Future.future();
    f1.setHandler(ar -> {
      System.out.println(Thread.currentThread().getName() + ": f1 handler");
    });
    f1.complete();

    Future<Void> f2 = Future.future();
    f2.setHandler(ar -> {
      System.out.println(Thread.currentThread().getName() + ": f2 handler");
    });
    f2.complete();

    CompositeFuture all = CompositeFuture.all(f1, f2);
    all.setHandler(ar -> {
      if(ar.succeeded()) {
        System.out.println(Thread.currentThread().getName() + ": all futures passed");
      }
      else {
        System.out.println(Thread.currentThread().getName() + ": at least one future failed");
      }
    });

  }
/*
output:
f1 handler
f2 handler
all futures passed
 */

  @Test
  public void testOneOfFuturesFailed() {

    Future<Void> f1 = Future.future();
    f1.setHandler(ar -> {
      System.out.println(Thread.currentThread().getName() + ": f1 handler");
    });
    f1.complete();

    Future<Void> f2 = Future.future();
    f2.setHandler(ar -> {
      System.out.println("f2 handler");
    });
    f2.fail("f2 fail");

    CompositeFuture all = CompositeFuture.all(f1, f2);
    all.setHandler(ar -> {
      if(ar.succeeded()) {
        System.out.println("all futures passed");
      }
      else {
        System.out.println("At least one future failed");
      }
    });

  }
/*
output:
f1 handler
f2 handler
At least one future failed
 */

}

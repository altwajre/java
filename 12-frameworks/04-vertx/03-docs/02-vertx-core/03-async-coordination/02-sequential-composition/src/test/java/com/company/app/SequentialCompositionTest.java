package com.company.app;

import io.vertx.core.Future;
import org.junit.Test;

/*
http://vertx.io/docs/vertx-core/java/#_sequential_composition

 */
class Tester {
  private Future<Void> task1() {
    Future<Void> future = Future.future();

    System.out.println("task1");
    future.complete();
    return future;
  }
  private Future<Void> task2() {
    Future<Void> future = Future.future();
    try {
      Thread.sleep(300);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("task2");
    future.complete();
    return future;
  }
  public void run(Future<Void> startFuture){
    // promises: task1().compose(v -> task2())
    Future<Void> steps = task1().compose(v -> task2());
    steps.setHandler(ar -> {
      if(ar.succeeded()) {
        System.out.println("pass");
        startFuture.complete();
      }
      else {
        System.out.println("fail");
        startFuture.fail(ar.cause());
      }
    });
  }
}

public class SequentialCompositionTest {
  @Test
  public void testSequentialComposition(){
    Tester tester = new Tester();
    tester.run(Future.future());
  }
/*
output:
task1
task2
pass
 */

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

    Future<Void> compose = f1.compose(v -> f2);
    compose.setHandler(ar -> {
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
main: f1 handler
main: f2 handler
main: all futures passed
 */
}
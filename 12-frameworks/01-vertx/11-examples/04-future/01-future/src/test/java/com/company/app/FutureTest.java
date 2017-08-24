package com.company.app;

import io.vertx.core.Future;
import org.junit.Test;

import static org.junit.Assert.*;

class FutureTester {
  public void run(Future<Void> future) {
    future.setHandler(ar -> {
      if(ar.succeeded()) {
        System.out.println("pass");
      }
      else {
        System.out.println("fail");
        future.fail(ar.cause());
      }
    });
    // Waiting for future to complete
    future.complete();
  }
}

public class FutureTest {

  @Test
  public void testFutureSetHandler(){
    Future<Void> future = Future.future();
    future.setHandler(ar -> {
      if(ar.succeeded()) {
        System.out.println("pass");
      }
      else {
        System.out.println("fail");
        future.fail(ar.cause());
      }
    });
    future.complete();
  }

  @Test
  public void testFutureTester() {
    FutureTester futureTester = new FutureTester();
    futureTester.run(Future.future());
  }
  /*
  output:
  pass
   */

}
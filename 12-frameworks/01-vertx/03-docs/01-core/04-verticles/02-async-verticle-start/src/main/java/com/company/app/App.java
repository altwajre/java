package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

/*
http://vertx.io/docs/vertx-core/java/#_asynchronous_verticle_start_and_stop

 */
class FirstVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> future) {

    System.out.println(Thread.currentThread().getName() + ": FirstVerticle.start() async is called");

    vertx.deployVerticle(new SecondVerticle(), ar -> {

      System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");

      if (ar.succeeded()) {
        // call future.complete() to signal that you're done
        future.complete();
      } else {
        future.fail(ar.cause());
      }
    });
  }
}

class SecondVerticle extends AbstractVerticle {

  @Override
  public void start() {
    System.out.println(Thread.currentThread().getName() + ": SecondVerticle.start() sync is called");
  }
}

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new FirstVerticle(), ar -> {
      vertx.undeploy(ar.result(), undeploy -> {
        vertx.close(); // prefer to use vertx.close() instead of System.exit(0);
      });
    });
  }
}
/*
output:
vert.x-eventloop-thread-0: FirstVerticle.start() async is called
vert.x-eventloop-thread-1: SecondVerticle.start() sync is called
vert.x-eventloop-thread-0: vertx.deployVerticle() handler
 */

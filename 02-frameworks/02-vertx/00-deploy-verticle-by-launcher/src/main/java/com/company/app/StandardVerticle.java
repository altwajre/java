package com.company.app;

import io.vertx.core.AbstractVerticle;

public class StandardVerticle extends AbstractVerticle {

  // Called when verticle is deployed
  @Override
  public void start() {
    System.out.println(Thread.currentThread().getName() + ": StandardVerticle.start() is called");

    vertx.runOnContext(v -> {
      System.out.println(Thread.currentThread().getName() + ": vertx.runOnContext() handler end");
    });
  }

  // Optional - called when verticle is undeployed
  @Override
  public void stop() {
    System.out.println(Thread.currentThread().getName() + ": StandardVerticle.stop() is called");
  }

}

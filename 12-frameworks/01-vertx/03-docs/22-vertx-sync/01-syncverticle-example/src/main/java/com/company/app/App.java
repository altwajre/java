package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.sync.SyncVerticle;

import static io.vertx.ext.sync.Sync.awaitEvent;

class MyVerticle extends SyncVerticle {
  @Override
  public void start() {
    System.out.println("Waiting for single event");

    long tid = awaitEvent(h -> vertx.setTimer(1000, h));
    System.out.println("Single event has fired.");
  }
}

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MyVerticle());
  }
}
/*
Waiting for single event
Waiting for single event
Single event has fired.
 */

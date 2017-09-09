package com.company.app;

import io.vertx.core.Vertx;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new MyVerticle());
  }
}
/*
curl http://localhost:8080/some/path
/some/path
 */

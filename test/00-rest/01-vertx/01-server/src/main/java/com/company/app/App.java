package com.company.app;

import io.vertx.core.Vertx;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WhiskyVerticle(), ar -> {

      System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");

      if (ar.succeeded()) {
        System.out.println("Deployment_id=" + ar.result());
      } else {
        System.out.println("Deployment failed");
      }
    });
  }
}

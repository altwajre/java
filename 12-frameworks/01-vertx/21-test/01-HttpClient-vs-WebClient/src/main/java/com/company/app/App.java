package com.company.app;

import io.vertx.core.Vertx;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new CustomerVerticle(), ar -> {
      if (!ar.succeeded()) {
        System.out.println("Deployment failed");
      }
      else {
//        vertx.undeploy(ar.result(), undeploy -> {
//          vertx.close(); // prefer to use vertx.close() instead of System.exit(0);
//        });
      }
    });
  }
}

package com.company.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class App {

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();

    Integer port = 8080;
    JsonObject config = new JsonObject()
        .put("http.port", port);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    vertx.deployVerticle(new WhiskyVerticle(), options, ar -> {

      System.out.println(Thread.currentThread().getName() + ": vertx.deployVerticle() handler");

      if (ar.succeeded()) {
        System.out.println("Deployment_id=" + ar.result());
      } else {
        System.out.println("Deployment failed");
      }
    });
  }
}

package com.company.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    JsonObject config = new JsonObject()
        .put("http.port", 8081);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    vertx.deployVerticle(new ProductVerticle(), options, ar -> {

      if (ar.succeeded()) {
        String result = ar.result();
        System.out.println(result);
      } else {
        System.out.println("Deployment failed");
      }
    });

  }
}

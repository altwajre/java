package com.company.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class App {
  public static void main(String[] args) {
    JsonObject config = new JsonObject()
        .put("http.port", 8080);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    Vertx.vertx().deployVerticle(new CustomerVerticle(), options);
  }
}

package com.company.app;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() {
    String name = config().getString("name", "world");
    vertx
        .createHttpServer()
        .requestHandler(req -> req.response()
            .end("Hello " + name + " from vertx verticle!"))
        .listen(8080);
  }
}

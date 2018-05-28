package com.company.app;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx
        .createHttpServer()
        .requestHandler(req -> req.response()
            .end("Hello from server"))
        .listen(8080, "localhost");
  }
}

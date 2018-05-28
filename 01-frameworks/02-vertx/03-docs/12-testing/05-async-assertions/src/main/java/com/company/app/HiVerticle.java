package com.company.app;

import io.vertx.core.AbstractVerticle;

public class HiVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx
        .createHttpServer()
        .requestHandler(req -> req.response()
            .end("Hello from HiVerticle"))
        .listen(8081, "localhost");
  }
}

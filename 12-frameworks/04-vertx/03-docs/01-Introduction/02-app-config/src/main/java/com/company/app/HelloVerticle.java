package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> future) {
    vertx
        .createHttpServer()
        .requestHandler(req -> req.response()
            .end("Hello from vertx verticle app config!"))
        .listen(
            // Retrieve the port for the configuration
            // default to 8080
            config().getInteger("http.port", 8080),
            result -> {
              if(result.succeeded()){
                future.complete();
              }
              else {
                future.fail(result.cause());
              }
            }
        );
  }
}

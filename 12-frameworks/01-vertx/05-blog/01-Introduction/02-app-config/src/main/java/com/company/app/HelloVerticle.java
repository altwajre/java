package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/*
Run with `-config`

$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json

 */
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

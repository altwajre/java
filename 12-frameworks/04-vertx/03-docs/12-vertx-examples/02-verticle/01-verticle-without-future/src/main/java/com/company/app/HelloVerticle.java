package com.company.app;

import io.vertx.core.AbstractVerticle;

public class HelloVerticle extends AbstractVerticle {

  @Override
  public void start() {

    System.out.println(Thread.currentThread().getName() + ": StandardVerticle.start() is called");

    String name = config().getString("name", "world");
    vertx
        .createHttpServer()
        .requestHandler(req -> {
          System.out.println(Thread.currentThread().getName() + ": HttpServer.requestHandler() is called");

          req.response()
              .end(Thread.currentThread().getName() + ": Hello " + name + " from vertx verticle!");
        })
        .listen(8080);
  }
}
/*
http://localhost:8080/
vert.x-eventloop-thread-0: Hello world from vertx verticle!
 */

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.TimeoutHandler;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    long timeout = 500;
    router.route().handler(TimeoutHandler.create(timeout));
    router.route("/some/path").handler(context -> {
      // Don't end it
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl -i http://localhost:8080/some/path
HTTP/1.1 503 Service Unavailable
Content-Length: 19

Service Unavailable
 */

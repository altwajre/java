package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    router.route().handler(context -> {
      // This handler will be called for every request
      final HttpServerResponse response = context.response();
      response.putHeader("content-type", "text/plain");

      // Write to the response and end it
      response.end("Hello World from Vertx-Web!");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080
Hello World from Vertx-Web!
 */

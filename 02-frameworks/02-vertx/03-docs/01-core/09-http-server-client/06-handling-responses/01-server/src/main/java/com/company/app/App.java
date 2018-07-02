package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx
        .vertx();

    HttpServer server = vertx
        .createHttpServer();

    server
        .requestHandler(request -> {

          HttpServerResponse response = request.response();

          if (request.method() == HttpMethod.POST) {
            request.bodyHandler(postData -> {
              System.out.println(Thread.currentThread().getName() + ": request.bodyHandler()=" + postData);
            });
          }

          response
              .putHeader("content-type", "text/html")
              .end("Hello from server - " + request.method());
        })
        // port and host
        .listen(8080, "localhost", ar -> {
          if (ar.succeeded()) {
            HttpServer result = ar.result();
            System.out.println("Server listening at " + result.actualPort());
          } else {
            System.out.println("Failed to bind!");
          }
        });
  }
}

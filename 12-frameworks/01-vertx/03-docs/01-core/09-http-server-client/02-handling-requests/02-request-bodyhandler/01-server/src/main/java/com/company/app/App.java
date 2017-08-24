package com.company.app;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
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

          System.out.println("uri: " + request.uri());
          System.out.println("path: " + request.path());
          System.out.println("query: " + request.query());
          MultiMap headers = request.headers();
          System.out.println("Headers: {Host: " + headers.get("HOST") + "}");
          System.out.println("host: " + request.host());
          System.out.println("params:\n" + request.params());
          System.out.println("remoteAddress:" + request.remoteAddress());
          System.out.println("absoluteURI: " + request.absoluteURI());

          request.bodyHandler(postData -> {
            System.out.println("request.bodyHandler(): " + postData);
          });

          response
              .setChunked(true)
              .write("Hello from server - " + request.method())
              .end();
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

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx
        .vertx();

    HttpServer server = vertx
        .createHttpServer();

    server
        .requestHandler(request -> {
          request
              .response()
              .end("Hello world");
        })
        // port and host
        .listen(8080, "localhost", ar -> {
          if(ar.succeeded()){
            HttpServer result = ar.result();
            System.out.println("Server listening at " + result.actualPort());
          }
          else {
            System.out.println("Failed to bind!");
          }
        });

  }
}

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx
        .vertx();

    HttpServer server = vertx
        .createHttpServer();

    server
        .requestHandler(request -> {

          if (request.method() == HttpMethod.POST) {

            // The body handler is called once when all the body has been received
            request.bodyHandler(body -> {
              System.out.println(body);
            });
          }

          request
              .response()
              .end("Hello from server: request.method=" + request.method() + " " + request.path());
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

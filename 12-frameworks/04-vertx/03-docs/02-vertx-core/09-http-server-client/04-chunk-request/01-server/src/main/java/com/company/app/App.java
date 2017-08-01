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

          if (request.method() == HttpMethod.POST) {

            request.endHandler(v -> {
              System.out.println(Thread.currentThread().getName() + ": request.endHandler() is called");
            });

          }

          request.handler(chunk -> {
            System.out.println(chunk);
          });

          HttpServerResponse response = request.response();

          response.setChunked(true);

          // Write some chunks
          for (int i = 0; i < 5; i++) {
            response.write("Server-Chunk-" + i);
          }

          response.end();


//          response
//              .end("Hello from server: request.method=" + request.method() + " " + request.path());
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

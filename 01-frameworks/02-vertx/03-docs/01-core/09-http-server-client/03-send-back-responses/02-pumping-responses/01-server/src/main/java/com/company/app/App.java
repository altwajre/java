package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.streams.Pump;

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

            /*
            Note:
            request.handler() is not called when using Pump.pump()
             */
            request.handler(chunk -> {
              System.out.println(chunk);
            });

            response.setChunked(true);
            Pump.pump(request, response).start();
            request.endHandler(v -> response.end());

          } else {
            response
                .setChunked(true)
                .write("Unknown Request method")
                .end();
          }
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

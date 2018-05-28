package com.company.app;

import io.vertx.core.MultiMap;
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

          System.out.println("uri: " + request.uri());
          System.out.println("path: " + request.path());
          System.out.println("query: " + request.query());
          MultiMap headers = request.headers();
          System.out.println("Headers: {Host: " + headers.get("HOST") + "}");
          System.out.println("host: " + request.host());
          System.out.println("params:\n" + request.params());
          System.out.println("remoteAddress:" + request.remoteAddress());
          System.out.println("absoluteURI: " + request.absoluteURI());

          request.endHandler(v -> {
            System.out.println(Thread.currentThread().getName() + ": req.endHandler() is called");
          });

          if (request.method() == HttpMethod.GET) {
            response
                .setChunked(true)
                .write("Hello from server - GET request");
            response.end();
          } else if (request.method() == HttpMethod.POST) {

            /*
            the handler() is called every time a chunk of the request body arrives

            When request handler is called, the request object does not have the entire request body at this point.
            Because the body may be very large (e.g. a file upload) and we don't generally want to buffer the entire body
            in memory before handing it to you, as that could cause the server to exhaust available memory.

             */
            request.handler(chunk -> {
              System.out.println(chunk);
            });

            response
                .setChunked(true)
                .write("Hello from server - POST request")
                .end();
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

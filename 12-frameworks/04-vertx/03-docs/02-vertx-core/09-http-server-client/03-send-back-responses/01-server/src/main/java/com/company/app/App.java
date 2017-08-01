package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

import java.io.File;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx
        .vertx();

    HttpServer server = vertx
        .createHttpServer();

    server
        .requestHandler(request -> {

          String path = request.path();
          System.out.println(path);

          HttpServerResponse response = request.response();

          if (path.contains("file")){
            System.out.println("test file");
            File file = new File("src/main/resources/json/parking.json");
            response.sendFile(file.getAbsolutePath());
          }
          else {
            System.out.println("test string");
            response
                .putHeader("content-type", "text/html")
                .setChunked(true)
                .write("Hello from server")
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

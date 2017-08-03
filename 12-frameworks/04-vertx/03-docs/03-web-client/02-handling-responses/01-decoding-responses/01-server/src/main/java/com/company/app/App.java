package com.company.app;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx
        .vertx();

    HttpServer server = vertx
        .createHttpServer();

    server
        .requestHandler(request -> {

          System.out.println("uri: " + request.uri());

          HttpServerResponse response = request.response();

          if (request.uri().contains("jsonObject")) {
            JsonObject jsonObject = new JsonObject().put("name", "jsonObject").put("age", "18");

            response.end(jsonObject.encode());
          } else if (request.uri().contains("/json")) {
            JsonObject jsonObject = new JsonObject().put("name", "json").put("age", "28");

            response.end(jsonObject.encode());
          } else if (request.uri().contains("/default")) {
            JsonObject jsonObject = new JsonObject().put("name", "default").put("age", "38");

            response.end(jsonObject.encode());
          }

          else {
            response.end();
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

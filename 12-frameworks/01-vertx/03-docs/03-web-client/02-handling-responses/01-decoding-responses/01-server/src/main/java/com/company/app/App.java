package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
  private String name;
  private Integer age;
}

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

          if (request.uri().contains("/pojoBodyCodec")) {
            User user = new User("pojoBodyCodec", 18);
            response.end(Json.encodePrettily(user));
          } else if (request.uri().contains("/jsonObjectBodyCodec")) {
            User user = new User("jsonObjectBodyCodec", 28);
            response.end(Json.encodePrettily(user));
          } else if (request.uri().contains("/defaultBodyCodec")) {
            JsonObject jsonObject = new JsonObject().put("name", "defaultBodyCodec").put("age", "38");
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

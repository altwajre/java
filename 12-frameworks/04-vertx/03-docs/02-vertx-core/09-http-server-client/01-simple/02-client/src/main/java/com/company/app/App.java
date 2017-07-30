package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();
    client.getNow(8080, "localhost", "/", response -> {
      System.out.println("StatusCode: " + response.statusCode());
      response.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });

  }
}

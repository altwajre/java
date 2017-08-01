package com.company.app;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    responseString(client);

    responseFile(client);

  }

  private static void responseFile(HttpClient client) {
    client.getNow(8080, "localhost", "/file", response -> {

      System.out.println("StatusCode: " + response.statusCode());

      response.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });
  }

  private static void responseString(HttpClient client) {
    client.getNow(8080, "localhost", "/", response -> {

      System.out.println("StatusCode: " + response.statusCode());
      MultiMap headers = response.headers();
      System.out.println("header content-type: " + headers.get("content-type"));

      response.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });
  }
}

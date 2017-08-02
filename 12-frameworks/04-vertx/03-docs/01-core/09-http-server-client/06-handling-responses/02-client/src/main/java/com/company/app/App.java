package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    getNow(client);
  }

  private static void getNow(HttpClient client) {
    client.getNow(8080, "localhost", "/getNow/some-uri", response -> {

      String threadName = Thread.currentThread().getName();

      System.out.println(threadName + ": StatusCode=" + response.statusCode());
      System.out.println(threadName + ": StatusMessage=" + response.statusMessage());
      System.out.println(threadName + ": Headers.content-type=" + response.headers().get("content-type"));

      response.bodyHandler(body -> {
        System.out.println(Thread.currentThread().getName() + ": Response body - " + body.toString());
      });
    });
  }
}

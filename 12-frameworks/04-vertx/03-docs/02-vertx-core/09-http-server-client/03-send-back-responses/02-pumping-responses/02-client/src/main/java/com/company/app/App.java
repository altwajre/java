package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpHeaders;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    String postData = "Hello from client";
    String postDataLen = String.valueOf(postData.length() / 2);
    client
        .post(8080, "localhost", "/post")
        .putHeader(HttpHeaders.CONTENT_LENGTH, postDataLen)
        .handler(resp -> {
          System.out.println("statusCode: " + resp.statusCode());
          resp.bodyHandler(body -> {
            System.out.println("Response body: " + body.toString());
          });
        })
        .write(postData)
        .end();

  }
}

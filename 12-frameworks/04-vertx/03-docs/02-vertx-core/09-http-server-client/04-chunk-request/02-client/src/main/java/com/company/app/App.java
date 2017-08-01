package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    HttpClientRequest request = client.request(HttpMethod.POST,
        8080,
        "localhost",
        "/requestPost",
        response -> {
          System.out.println("StatusCode: " + response.statusCode());
          response.bodyHandler(body -> {
            System.out.println("Response body: " + body.toString());
          });
        });

    request.setChunked(true);

    // Write some chunks
    for (int i = 0; i < 5; i++) {
      request.write("this-is-chunk-" + i + "\n");
    }

    request.end();

  }
}

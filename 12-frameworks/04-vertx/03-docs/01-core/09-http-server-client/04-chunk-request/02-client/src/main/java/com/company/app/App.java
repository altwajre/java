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
        "/requestPost");

    request.handler(response -> {
      System.out.println("StatusCode: " + response.statusCode());
      response.endHandler(v -> {
        System.out.println(Thread.currentThread().getName() + ": response.endhandler() is called");
      });
      response.handler(chunk -> {
        System.out.println(chunk);
      });
    });

    request.setChunked(true);

    // Write some chunks
    for (int i = 0; i < 3; i++) {
      request.write("Client-Chunk-" + i + "\n");
    }

    request.end();

  }
}

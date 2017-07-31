package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    requestPost(client);

  }

  private static void requestPost(HttpClient client) {
    HttpClientRequest req = client.request(HttpMethod.POST, 8080, "localhost",
        "/requestPost/b/c/page.html?param1=abc&param2=xyz",
        resp -> {
      System.out.println("statusCode: " + resp.statusCode());
      resp.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });

    req.setChunked(true);

    /*
    BUG:
    1. both req.write() calls just send data as once instead of sending separately twice
    2. Content-Length does not work because `Benny` is sent to server

     */
    req.headers().set("Content-Length", String.valueOf(3));

    String names[] = {"Tom", "Benny"};

    for (int i = 0; i < 2; i++) {
      Buffer buffer = Buffer.buffer(names[i]);
      System.out.println(buffer);
      req.write(buffer);
    }

    req.end();
  }

}

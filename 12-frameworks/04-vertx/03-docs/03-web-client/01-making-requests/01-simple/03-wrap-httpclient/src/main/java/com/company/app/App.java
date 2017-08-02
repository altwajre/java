package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx
        .vertx();

    HttpClient httpClient = vertx.createHttpClient();

    WebClient client = WebClient.wrap(httpClient);

    // Send a GET request
    client
        .get(8080, "localhost", "/clientget/b/c/page.html?param1=abc&param2=xyz")
        .addQueryParam("param3", "123")
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }
}

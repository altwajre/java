package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class App {
  public static void main(String[] args) {
    Vertx vertx = Vertx
        .vertx();

    WebClient client = WebClient.create(vertx);

    getRequest(client);

    headRequest(client);

  }

  private static void getRequest(WebClient client) {
    // Send a GET request
    client
        .get(8080, "localhost", "/clientget/b/c/page.html?param1=abc&param2=xyz")
        // add param3
        .addQueryParam("param3", "123")
        // overwrite param3 and add param4
        .uri("/clientget/b/c/page.html?param1=abc&param2=xyz&param3=123&param4=456")
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": " + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

  private static void headRequest(WebClient client) {
    // Send a HEAD request
    client
        .head(8080, "localhost", "/clienthead/b/c/page.html?param1=abc&param2=xyz")
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": " + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

}

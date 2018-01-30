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

    client.get(8080, "localhost", "/some-uri")
        // timeout
        .timeout(800)
        // no exception
//        .timeout(2800)
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": Response.StatusCode=" + response.statusCode());
            System.out.println(threadName + ": Response.Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });

  }
}

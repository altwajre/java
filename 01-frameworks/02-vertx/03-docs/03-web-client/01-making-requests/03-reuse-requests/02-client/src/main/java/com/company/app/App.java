package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx
        .vertx();

    WebClient client = WebClient.create(vertx);

    HttpRequest<Buffer> request = client.get(8080, "localhost", "/some-uri");

    request
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });

    request
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

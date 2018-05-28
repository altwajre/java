package com.company.app;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
  private String name;
  private Integer age;
}

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx
        .vertx();

    WebClient client = WebClient.create(vertx);

    sendBuffer(client);

    sendStream(vertx, client);

    sendJsonObject(client);

    sendJson(client);

    sendForm(client);

  }

  private static void sendForm(WebClient client) {
    MultiMap form = MultiMap.caseInsensitiveMultiMap();
    form.set("name", "Harry");
    form.set("age", "38");

    // Submit the form as a form URL encoded body
    client
        .post(8080, "localhost", "/sendForm")
        .putHeader("content-type", "multipart/form-date")
        .sendForm(form, ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

  private static void sendJson(WebClient client) {
    // Use sendJson() to map a POJO (Plain Old Java Object) to a Json object by using Json.encode method
    client
        .post(8080, "localhost", "/sendJson")
        .sendJson(new User("Dick", 28), ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

  private static void sendJsonObject(WebClient client) {
    client
        .post(8080, "localhost", "/sendJsonObject")
        .sendJsonObject(new JsonObject()
        .put("name", "Tom")
        .put("age", "18"), ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

  /*
  Sending a single buffer is useful but often you don’t want to load fully the content in memory because it may be
  too large or you want to handle many concurrent requests and want to use just the minimum for each request.
  For this purpose the web client can send ReadStream<Buffer> (e.g a AsyncFile is a ReadStream<Buffer>`)
  with the sendStream method
   */
  private static void sendStream(Vertx vertx, WebClient client) {
    File file = new File("src/main/resources/json/parking.json");
    AsyncFile asyncFile = vertx.fileSystem().openBlocking(file.getAbsolutePath(), new OpenOptions());
    client
        .post(8080, "localhost", "/sendStream")
        .sendStream(asyncFile, ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": StatusMessage=" + response.statusMessage());
            System.out.println(threadName + ": Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }

  private static void sendBuffer(WebClient client) {
    client
        .post(8080, "localhost", "/sendBuffer")
        .sendBuffer(Buffer.buffer("Hello from Client"), ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());
            System.out.println(threadName + ": Body=" + response.body());
          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }
}

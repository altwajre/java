package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;

public class App {
  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    HttpClient client = vertx.createHttpClient();

    getNow(client);

    post(client);

    requestPost(client);

  }

  private static void requestPost(HttpClient client) {
    HttpClientRequest request = client.request(HttpMethod.POST, 8080, "localhost", "/requestPost", response -> {
      System.out.println("statusCode: " + response.statusCode());
      response.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });

    request.setChunked(true);

    String names[] = {"Tom", "Benny"};

    for (int i = 0; i < 2; i++) {
      String name = names[i];
      System.out.println(name);
      request.write(name + "\n");
    }

    request.end();
  }

  private static void post(HttpClient client) {
    String postData = "Hello from client";
    String postDataLen = String.valueOf(postData.length() / 2);
    client
        .post(8080, "localhost", "/post")
//        .putHeader(HttpHeaders.CONTENT_TYPE, HttpHeaders.APPLICATION_X_WWW_FORM_URLENCODED)
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

  private static void getNow(HttpClient client) {
    client.getNow(8080, "localhost", "/getNow/b/c/page.html?param1=abc&param2=xyz", response -> {
      System.out.println("StatusCode: " + response.statusCode());
      response.bodyHandler(body -> {
        System.out.println("Response body: " + body.toString());
      });
    });
  }
}

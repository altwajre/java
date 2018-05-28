package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    pojoBodyCodec(client);

    jsonObjectBodyCodec(client);

    defaultBodyCodec(client);

  }

  private static void pojoBodyCodec(WebClient client) {
    client
        .get(8080, "localhost", "/pojoBodyCodec")
        .as(BodyCodec.json(User.class))
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {

            HttpResponse<User> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());

            User body = response.body();
            System.out.println(threadName + ": Body=" + body);

          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }
/*
vert.x-eventloop-thread-0: StatusCode=200
vert.x-eventloop-thread-0: Body=User(name=pojoBodyCodec, age=18)
 */

  private static void jsonObjectBodyCodec(WebClient client) {
    client
        .get(8080, "localhost", "/jsonObjectBodyCodec")
        .as(BodyCodec.jsonObject())
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {

            HttpResponse<JsonObject> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());

            JsonObject body = response.body();

            System.out.println(threadName + ": Body=" + body);

          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }
/*
vert.x-eventloop-thread-1: StatusCode=200
vert.x-eventloop-thread-1: Body={"name":"jsonObjectBodyCodec","age":"28"}
 */

  // default BodyCodec
  private static void defaultBodyCodec(WebClient client) {
    client
        .get(8080, "localhost", "/defaultBodyCodec")
        .send(ar -> {
          String threadName = Thread.currentThread().getName();
          if (ar.succeeded()) {

            HttpResponse<Buffer> response = ar.result();
            System.out.println(threadName + ": StatusCode=" + response.statusCode());

            Buffer body = response.body();
            System.out.println(threadName + ": Body=" + body);

          } else {
            System.out.println(threadName + ": Error=" + ar.cause());
          }
        });
  }
/*
vert.x-eventloop-thread-2: StatusCode=200
vert.x-eventloop-thread-2: Body={"name":"defaultBodyCodec","age":"38"}
 */
}

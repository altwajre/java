package com.company.app;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.ArrayList;
import java.util.List;

public class WebClientHelper<T> {

  private static final String USER_AGENT = "mozilla/5.0";
  private static final String APP_JSON = "application/json";
  private final Class<T> clazz;
  private final Vertx vertx = Vertx.vertx();

  public WebClientHelper(Class<T> clazz) {
    this.clazz = clazz;
  }

  public void getList(String uri, Handler<AsyncResult<List<T>>> handler) {
    WebClient client = WebClient.create(vertx);
    client.getAbs(uri)
      .putHeader("user-agent", USER_AGENT)
      .putHeader("accept", APP_JSON)
      .send(ar -> {
        HttpResponse<Buffer> response = ar.result();
        Future<List<T>> fut = Future.future();
        fut.setHandler(handler);
        client.close();
        processListResponse(ar, fut, 200);
      });
  }

  public void get(String uri, Handler<AsyncResult<T>> handler) {
    WebClient client = WebClient.create(vertx);
    client.getAbs(uri)
      .putHeader("user-agent", USER_AGENT)
      .putHeader("accept", APP_JSON)
      .send(ar -> {
        Future<T> fut = Future.future();
        fut.setHandler(handler);
        client.close();
        processResponse(ar, fut, 200);
      });
  }

  public void post(String uri, JsonNode obj, Handler<AsyncResult<T>> handler) {
    WebClient client = WebClient.create(vertx);
    String json = (obj == null) ? "{}" : Json.encodePrettily(obj);
    Buffer buf = Buffer.buffer(json);
    client.postAbs(uri)
      .putHeader("user-agent", USER_AGENT)
      .putHeader("content-type", APP_JSON)
      .putHeader("accept", APP_JSON)
      .sendBuffer(buf, ar -> {
        Future<T> fut = Future.future();
        fut.setHandler(handler);
        client.close();
        processResponse(ar, fut, 201);
      });
  }

  public void put(String uri, JsonNode obj, Handler<AsyncResult<T>> handler) {
    WebClient client = WebClient.create(vertx);
    String json = Json.encodePrettily(obj);
    Buffer buf = Buffer.buffer(json);
    client.putAbs(uri)
      .putHeader("user-agent", USER_AGENT)
      .putHeader("content-type", APP_JSON)
      .putHeader("accept", APP_JSON)
      .putHeader("etag", String.valueOf(obj.get("version").asText()))
      .sendBuffer(buf, ar -> {
        Future<T> fut = Future.future();
        fut.setHandler(handler);
        client.close();
        processResponse(ar, fut, 200);
      });
  }

  private void processResponse(AsyncResult<HttpResponse<Buffer>> ar, Future<T> future,
    int expectedStatus) {
    HttpResponse<Buffer> resp = ar.result();
    if (ar.succeeded() && resp.statusCode() == expectedStatus) {
      final JsonObject body = resp.bodyAsJsonObject();
      body.remove("uri");
      T resource = Json.mapper.convertValue(body, clazz);
      future.complete(resource);
    } else {
      if (ar.failed()) {
        future.fail(ar.cause());
      } else {
        future.fail(ar.cause());
      }
    }
  }

  private void processListResponse(AsyncResult<HttpResponse<Buffer>> ar, Future<List<T>> future,
    int expectedStatus) {
    HttpResponse<Buffer> resp = ar.result();
    if (ar.succeeded() && resp.statusCode() == expectedStatus) {
      JavaType type = Json.mapper.getTypeFactory().constructCollectionType(List.class, clazz);
      List<T> resourceList = new ArrayList<>();
      JsonArray arr = resp.bodyAsJsonArray();
      arr.forEach((node) -> {
        ((JsonObject) node).remove("uri");
        T resource = Json.mapper.convertValue(node, clazz);
        resourceList.add(resource);
      });
      future.complete(resourceList);
    } else {
      if (ar.failed()) {
        future.fail(ar.cause());
      } else {
        future.fail(ar.cause());
      }
    }
  }
}

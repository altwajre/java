package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/*
http://vertx.io/docs/vertx-web/java/#_request_body_handling

Checkout `Handling file uploads` on your own
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    // Enables the reading of the request body for all routes under "/api/customers"
    router.route("/api/customers*").handler(BodyHandler.create()
        .setBodyLimit(5000)
        .setMergeFormAttributes(true));

    router.post("/api/customers").handler(context -> {
      final Customer customer = Json.decodeValue(context.getBodyAsString(), Customer.class);
      context.response()
          .setStatusCode(201)
          .putHeader("content-type", "application/json")
          .end(Json.encodePrettily(customer));
    });

    server.requestHandler(router::accept)
        .listen(8080);
  }
}
/*
curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"id": "1", "name": "Tom", "age": "18"}'
{
  "id" : 1,
  "name" : "Tom",
  "age" : 18
}
 */

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_routing_based_on_mime_type_of_request
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    router.route("/some/path/")
        .consumes("application/json")
        .handler(context -> {
          final String contentType = context.request().getHeader("content-type");
          context.response().end("hello " + contentType + "\n");
        });

    // multiple mime types matches
    router.route()
        .consumes("text/html")
        .consumes("text/plain")
        .handler(context -> {
          final String contentType = context.request().getHeader("content-type");
          context.response().end("hello " + contentType + "\n");
        });

    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl -H 'content-type: application/json' http://localhost:8080/some/path/
hello application/json

curl -H 'content-type: text/html' http://localhost:8080/some/path/
hello text/html
curl -H 'content-type: text/plain' http://localhost:8080/some/path/
hello text/plain
 */

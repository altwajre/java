package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_routing_based_on_mime_type_of_request

Wildcards matching
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    // sub-type wildcards matching
    router.route().consumes("text/*")
        .handler(context -> {
          final String contentType = context.request().getHeader("content-type");
          context.response().end("hello " + contentType + "\n");
        });

    // top level type wildcards matching
    router.route("/some/path/").consumes("*/json")
        .handler(context -> {
          final String contentType = context.request().getHeader("content-type");
          context.response().end("hello " + contentType + "\n");
        });

    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl -H 'content-type: text/html' http://localhost:8080/some/path/
hello text/html
curl -H 'content-type: text/plain' http://localhost:8080/some/path/
hello text/plain

curl -H 'content-type: application/json' http://localhost:8080/some/path/
hello application/json
 */

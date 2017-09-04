package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_routing_with_regular_expressions

Regular expressions can be used to match URI paths in routes
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    final Route route = router.route().pathRegex(".*foo");
    route.handler(context -> {
      // This handler will be called for:

      // /some/path/foo
      // /foo
      // /foo/bar/wibble/foo
      // /bar/foo

      // But not:
      // /bar/wibble
      context.response().end("hello " + context.request().uri() + " from routing with regex\n");
    });

    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path/foo
hello /some/path/foo from routing with regex

curl http://localhost:8080/foo
hello /foo from routing with regex

curl http://localhost:8080/foo/bar/wibble/foo
hello /foo/bar/wibble/foo from routing with regex

curl http://localhost:8080/bar/foo
hello /bar/foo from routing with regex

curl http://localhost:8080/bar/wibble
<html><body><h1>Resource not found</h1></body></html>
 */

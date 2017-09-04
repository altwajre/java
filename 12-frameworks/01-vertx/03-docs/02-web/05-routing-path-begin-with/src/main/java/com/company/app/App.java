package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_routing_by_paths_that_begin_with_something

Often you want to route all requests that begin with a certain path.
A simple way is to use an asterisk * at the end of the path when declaring the route path.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
//    final Route route = router.route().path("/some/path/*");
    final Route route = router.route("/some/path/*");
    route.handler(context -> {
      // This handler will be called for any path that starts with
      // `/some/path/`, e.g.

      // `/some/path`
      // `/some/path/`
      // `/some/path/subdir`
      // `/some/path/subdir/blah.html`

      // but not:
      // `/some/bath`
      final HttpServerResponse response = context.response();
      response.end("hello from /some/path/*\n");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path
hello from /some/path/*

curl http://localhost:8080/some/path/
hello from /some/path/*

curl http://localhost:8080/some/path/subdir
hello from /some/path/*

curl http://localhost:8080/some/path/subdir/blah.html
hello from /some/path/*

curl http://localhost:8080/some/bath
<html><body><h1>Resource not found</h1></body></html>
 */

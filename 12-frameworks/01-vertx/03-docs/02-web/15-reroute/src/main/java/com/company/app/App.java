package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_reroute

Note rerouting based on method might introduce security issues for example a usually safe GET request can become a DELETE.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    router.route("/some/path").handler(context -> {
      context.next();
    });
    router.route("/some/path/B").handler(context -> {
      context.response().end(context.request().uri() + "\n");
    });
    router.route("/some/path").handler(context -> {
      context.reroute("/some/path/B");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path
/some/path/B
 */

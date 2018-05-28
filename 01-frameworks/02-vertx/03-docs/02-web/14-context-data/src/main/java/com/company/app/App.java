package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    router.route("/some/path").handler(context -> {
      context.put("1", "Tom");
      context.next();
    });
    router.route("/some/path").handler(context -> {
      context.put("2", "Dick");
      context.next();
    });
    router.route("/some/path").handler(context -> {
      String result  = context.get("1") + ", ";
      result += context.get("2");
      context.response().end(result + "\n");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path
Tom, Dick
 */

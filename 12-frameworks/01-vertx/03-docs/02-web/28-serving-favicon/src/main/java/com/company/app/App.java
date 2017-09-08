package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.FaviconHandler;

/*
http://vertx.io/docs/vertx-web/java/#_serving_favicons
 */
public class App {
  public static void main(String[] args) {
    String path = "src/main/resources/favicon.ico";

    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    router.route().handler(FaviconHandler.create(path));

    router.route("/some/path").handler(context -> {
      context.response().end(context.request().uri() + "\n");
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl -i http://localhost:8080/some/path
HTTP/1.1 200 OK
Content-Length: 11

/some/path
 */

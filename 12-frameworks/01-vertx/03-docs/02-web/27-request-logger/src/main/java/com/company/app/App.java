package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerFormat;
import io.vertx.ext.web.handler.LoggerHandler;

/*
http://vertx.io/docs/vertx-web/java/#_request_logger
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    final LoggerHandler loggerHandler = LoggerHandler.create(LoggerFormat.TINY);
    router.route().handler(loggerHandler);

    router.route("/some/path").handler(context -> {
      context.response().end(context.request().uri() + "\n");
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path
/some/path

LOGGER - LoggerHandler.create(LoggerFormat.TINY)
Sep 07, 2017 8:25:35 PM io.vertx.ext.web.handler.impl.LoggerHandlerImpl
INFO: GET /some/path 200 11 - 14 ms
 */

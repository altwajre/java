package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ResponseTimeHandler;

/*
http://vertx.io/docs/vertx-web/java/#_response_time_handler
 */
public class MyVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {
    final Router router = Router.router(vertx);

    router.route().handler(ResponseTimeHandler.create());
    router.route("/some/path").handler(context -> {
      vertx.setTimer(250, tid -> {
        context.response().end(context.request().uri() + "\n");
      });
    });

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(8080, ar -> {
          if (ar.succeeded()) {
            future.complete();
          } else {
            future.fail(ar.cause());
          }
        });
  }
}

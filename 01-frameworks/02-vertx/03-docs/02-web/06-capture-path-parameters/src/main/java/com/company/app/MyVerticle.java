package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MyVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {

    final Router router = Router.router(vertx);
    final Route route = router.route(HttpMethod.POST, "/catalogue/products/:producttype/:productid");
    route.handler(context -> {
      final String producttype = context.request().getParam("producttype");
      final String productid = context.request().getParam("productid");

      context.response().end(producttype + ": " + productid + "\n");
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

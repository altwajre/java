package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;

public class MyVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {

    final JsonObject tom = new JsonObject().put("1", "Tom");
    final Buffer buffer = tom.toBuffer();

    final Router router = Router.router(vertx);

    router.route().handler(ResponseContentTypeHandler.create());

    router
        .route("/some/path")
        .produces("application/json")
        .handler(context -> {
          context.response().end(buffer);
        });

    router
        .get("/api/books")
        .produces("text/xml")
        .produces("application/json")
        .handler(context -> {
          if (context.getAcceptableContentType().equals("text/xml")) {
            context.response().end("ContentType: text/xml");
          } else {
            context.response().end("ContentType: application/json");
          }
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

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_sub_routers
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    Router restAPI = Router.router(vertx);

    restAPI.get("/products/:productID").handler(context -> {
      context.response().end("Sub-Router GET\n");
    });

    restAPI.put("/products/:productID").handler(context -> {
      context.response().end("Sub-Router PUT\n");
    });

    restAPI.delete("/products/:productID").handler(context -> {
      context.response().end("Sub-Router DELETE\n");
    });

    final Router mainRouter = Router.router(vertx);
    mainRouter.mountSubRouter("/productsAPI", restAPI);

    server.requestHandler(mainRouter::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/productsAPI/products/product1234
Sub-Router GET
 */

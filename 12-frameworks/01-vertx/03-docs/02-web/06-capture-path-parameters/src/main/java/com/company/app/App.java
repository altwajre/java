package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_capturing_path_parameters

It's possible to match paths using placeholders for parameters which are then available in the request params.
The placeholders consist of `:` followed by the parameter name. Parameter names consist of any alphabetic character,
numeric character or underscore.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    final Route route = router.route(HttpMethod.POST, "/catalogue/products/:producttype/:productid/");
    route.handler(context -> {
      final String producttype = context.request().getParam("producttype");
      final String productid = context.request().getParam("productid");

      context.response().end(producttype + ": " + productid + "\n");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl -X POST http://localhost:8080/catalogue/products/tools/drill123/
tools: drill123
 */

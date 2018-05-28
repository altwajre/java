package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_route_order

By default routes are matched in the order they are added to the router.
If you want to override the default ordering for routes, you can do so using order, specifying an integer value.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    final Route route1 = router.route("/some/path").order(1).handler(context -> {
      final HttpServerResponse response = context.response();
      response.write("route1\n");
      // Call the next matching route after a 2 second delay
      context.vertx().setTimer(2000, tid -> context.next());
    });

    final Route route2 = router.route("/some/path").order(0).handler(context -> {
      final HttpServerResponse response = context.response();
      response.setChunked(true);
      response.write("route2\n");
      // Call the next matching route after a 2 second delay
      context.vertx().setTimer(2000, tid -> context.next());
    });

    final Route route3 = router.route("/some/path").order(2).handler(context -> {
      final HttpServerResponse response = context.response();
      response.write("route3\n");
      // Now end the response
      context.response().end();
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/path
route2
route1
route3
 */

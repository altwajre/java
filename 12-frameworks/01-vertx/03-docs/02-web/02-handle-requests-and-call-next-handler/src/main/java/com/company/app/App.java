package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_handling_requests_and_calling_the_next_handler

All this happens without any thread blocking.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
    final Route route1 = router.route("/some/path").handler(context -> {
      final HttpServerResponse response = context.response();
      // enable chunked responses because we will be adding data as we execute over other handlers.
      // This is only required once and only if several handlers do output.
      response.setChunked(true);

      response.write("route1\n");
      // Call the next matching route after a 2 second delay
      context.vertx().setTimer(2000, tid -> context.next());
    });

    final Route route2 = router.route("/some/path").handler(context -> {
      final HttpServerResponse response = context.response();
      response.write("route2\n");
      // Call the next matching route after a 2 second delay
      context.vertx().setTimer(2000, tid -> context.next());
    });

    final Route route3 = router.route("/some/path").handler(context -> {
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
route1
route2
route3
 */

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/*
We can't call blocking code in a normal handler. We need to use blocking handler.
A blocking handler looks just like a normal handler but it’s called by Vert.x using a thread from the worker pool not using an event loop.
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    router.route("/some/path").blockingHandler(context -> {
      // Do something that might take some time synchronously
      blockingCode();

      final HttpServerResponse response = context.response();
      response.setChunked(true);
      response.write("route1\n");

      // Now call the next handler
      context.next();
    });

    router.route("/some/path").blockingHandler(context -> {
      blockingCode();

      final HttpServerResponse response = context.response();
      response.write("route2\n");
      context.next();
    });

    router.route("/some/path").blockingHandler(context -> {
      blockingCode();

      final HttpServerResponse response = context.response();
      response.write("route3\n");
      context.response().end();
    });

    server.requestHandler(router::accept).listen(8080);

  }

  private static void blockingCode() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
/*
curl http://localhost:8080/some/path
route1
route2
route3
 */

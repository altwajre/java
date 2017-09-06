package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_error_handling

- If an exception occurs, a failure with status code 500 being signalled (Internal Server Error)
- In regular handler, context.fail(403) to pass the failure code to failureHandler()
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    router.get("/somepath/path1").handler(context -> {
      throw new RuntimeException("Something happened!");
    });

    router.get("/somepath/path2").handler(context -> {
      context.fail(403);
    });

    // Failure handler: this will get called for any failures in the above handlers
    router.get("/somepath/*").failureHandler(context -> {
      final int statusCode = context.statusCode();
      System.out.println(statusCode);
      context.response().setStatusCode(statusCode).end("Sorry! Not today\n");
    });

    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/somepath/path2
Sorry! Not today
curl http://localhost:8080/somepath/path1
Internal Server Error
 */

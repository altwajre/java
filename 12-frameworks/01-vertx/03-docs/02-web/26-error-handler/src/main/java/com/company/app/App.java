package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;

/*
http://vertx.io/docs/vertx-web/java/#_error_handler
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    router.get("/some/fail").handler(context -> {
      context.fail(403);
    });

    // Failure handler
    router.route("/some/*").failureHandler(ErrorHandler.create());

    // below will show "Sorry! Not today" instead
//    router.get("/some/*").failureHandler(context -> {
//      final int statusCode = context.statusCode();
//      System.out.println(statusCode);
//      context.response().setStatusCode(statusCode).end("Sorry! Not today\n");
//    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl http://localhost:8080/some/fail
Error 403: Forbidden
 */

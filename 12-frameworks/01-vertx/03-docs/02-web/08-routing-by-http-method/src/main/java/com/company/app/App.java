package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_routing_by_http_method

By default a route will match all HTTP methods
If you want a route to only match for a specific HTTP method you can use method
or you can use router.get(), router.post(), router.put() ...
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);
//    final Route route = router.route("/some/path/").method(HttpMethod.POST);
    final Route route = router.route(HttpMethod.POST, "/some/path/");
    route.handler(context -> {
      context.response().end("hello POST\n");
    });

    router.get("/some/path/").handler(context -> {
      context.response().end("hello GET\n");
    });

    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl -X POST http://localhost:8080/some/path/
hello POST
curl http://localhost:8080/some/path/
hello GET
 */

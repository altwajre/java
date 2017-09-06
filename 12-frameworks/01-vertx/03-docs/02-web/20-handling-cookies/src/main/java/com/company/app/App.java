package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CookieHandler;

/*
http://vertx.io/docs/vertx-web/java/#_handling_cookies
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    router.route().handler(CookieHandler.create());

    router.route("/some/path").handler(context -> {
      final Cookie cookie = context.getCookie("1");
      final String cookieValue = cookie.getValue();

      // add cookie
      context.addCookie(Cookie.cookie("2", "Dick"));
      context.response().end(cookieValue + "\n");
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
http://www.linuxask.com/questions/show-http-response-header-using-curl

curl -i http://localhost:8080/some/path -H 'cookie: 1=Tom'
HTTP/1.1 200 OK
Content-Length: 4
Set-Cookie: 2=Dick

Tom
 */

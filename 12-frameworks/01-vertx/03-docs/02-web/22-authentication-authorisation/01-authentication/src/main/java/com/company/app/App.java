package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.shiro.ShiroAuth;
import io.vertx.ext.auth.shiro.ShiroAuthRealmType;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BasicAuthHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.handler.UserSessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    // Create cookie handler first
    router.route().handler(CookieHandler.create());

    // Create session handler
    router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

    JsonObject authConfig = new JsonObject().put("properties_path", "classpath:login/loginusers.properties");
    final ShiroAuth authProvider = ShiroAuth.create(vertx, ShiroAuthRealmType.PROPERTIES, authConfig);
    router.route().handler(UserSessionHandler.create(authProvider));
    router.route("/private/*").handler(BasicAuthHandler.create(authProvider));

    router.route("/some/path").handler(context -> {
      context.response().end(context.request().uri() + "\n");
    });

    router.route("/private/somepath").handler(context -> {
      // This will require a login
      // This will have the value true
      System.out.println(context.user());
      context.response().end(context.request().uri() + "\n");
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);

  }
}
/*
curl -i http://localhost:8080/private/somepath -H 'Authorization: Basic dGltOmRlbGljaW91czpzYXVzYWdlcw=='
HTTP/1.1 200 OK
Content-Length: 18
Set-Cookie: vertx-web.session=3eac28f5b339d6772c08e3deec5979f3; Path=/

/private/somepath
 */

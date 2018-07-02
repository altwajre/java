package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

/*
http://vertx.io/docs/vertx-web/java/#_handling_sessions

Default timeout is 30 minutes
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    // Create cookie handler first
    router.route().handler(CookieHandler.create());

    // Create a session store
    final LocalSessionStore store = LocalSessionStore.create(vertx);

    final SessionHandler sessionHandler = SessionHandler.create(store);

    router.route().handler(sessionHandler);

    router.route("/some/path").handler(context -> {
      final Session session = context.session();
      session.put("1", "Tom");

      final String tom = session.get("1");
      System.out.println("session.get('1')=" + tom);

      final String remove = session.remove("1");
      System.out.println("session.remove('1')=" + remove);

      context.response().end();
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl -i http://localhost:8080/some/path -H 'cookie: 1=Tom'
HTTP/1.1 200 OK
Content-Length: 0
Set-Cookie: vertx-web.session=552bc898c2d07e61e5b9b0698231b9f6; Path=/
 */

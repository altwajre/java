package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.LanguageHeader;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_localization
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final HttpServer server = vertx.createHttpServer();
    final Router router = Router.router(vertx);

    router.get("/localized").handler(context -> {
      for(LanguageHeader language : context.acceptableLanguages()) {
        System.out.println(language.tag());
        switch (language.tag()) {
          case "en":
            context.response().end("en\n");
            return;
          case "fr":
            context.response().end("fr\n");
            return;
          case "pt":
            context.response().end("pt\n");
            return;
          case "es":
            context.response().end("es\n");
            return;
        }
      }
      context.response().end("Sorry don't speak: " + context.preferredLanguage() + "\n");
    });
    server.requestHandler(router::accept).listen(8080);
  }
}
/*
curl -H 'Accept-Language:en' http://localhost:8080/localized
en
 */

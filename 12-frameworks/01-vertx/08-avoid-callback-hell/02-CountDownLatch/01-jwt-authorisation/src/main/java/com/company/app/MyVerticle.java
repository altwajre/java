package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class MyVerticle extends AbstractVerticle {
  @Override
  public void start(Future<Void> future) {
    final Router router = Router.router(vertx);

    JsonObject authConfig = new JsonObject().put("keyStore", new JsonObject()
        .put("type", "jceks")
        .put("path", "keystore.jceks")
        .put("password", "secret"));

    final JWTAuth authProvider = JWTAuth.create(vertx, authConfig);

    router.route("/login/:username/:password").handler(context -> {
      if ("paulo".equals(context.request().getParam("username"))
          && "secret".equals(context.request().getParam("password"))) {
        // issue token
        System.out.println("issue token");
        context.response()
            .end(authProvider
                .generateToken(new JsonObject()
                    .put("sub", "paulo"), new JWTOptions()) + "\n");
      } else {
        context.fail(401);
      }
    });

    router.route("/protected/*").handler(JWTAuthHandler.create(authProvider));

    router.route("/protected/somepath").handler(context -> {
      final String paulo = context.user().principal().getString("sub");
      System.out.println(paulo);
      context.response().end("paulo accessed " + context.request().uri() + "\n");
    });

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(8080, ar -> {
          if (ar.succeeded()) {
            future.complete();
          } else {
            future.fail(ar.cause());
          }
        });
  }
}

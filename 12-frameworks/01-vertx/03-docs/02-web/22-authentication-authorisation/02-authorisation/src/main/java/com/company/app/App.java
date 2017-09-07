package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.web.Router;

/*
http://vertx.io/docs/vertx-web/java/#_jwt_authorisation

Issue tokens
 */
public class App {
  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    final Router router = Router.router(vertx);

    JsonObject authConfig = new JsonObject().put("keyStore", new JsonObject()
        .put("type", "jceks")
        .put("path", "keystore.jceks")
        .put("password", "secret"));

    final JWTAuth authProvider = JWTAuth.create(vertx, authConfig);

    router.route("/login/:username/:password").handler(context -> {
      if("paulo".equals(context.request().getParam("username"))
          && "secret".equals(context.request().getParam("password"))) {
        context.response().end(authProvider.generateToken(new JsonObject().put("sub", "paulo"), new JWTOptions()) + "\n");
      }
      else {
        context.fail(401);
      }
    });

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
curl -i http://localhost:8080/login/paulo/secret
HTTP/1.1 200 OK
Content-Length: 125

eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXVsbyIsImlhdCI6MTUwNDc2MDA2MX0.SJ-3LvbJpnJA0n69d1v6gv8PdAcVQiQ_bcEsvHq9DMs
 */

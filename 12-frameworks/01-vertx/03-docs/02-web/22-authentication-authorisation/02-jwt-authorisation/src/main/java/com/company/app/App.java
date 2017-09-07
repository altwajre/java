package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.RedirectAuthHandler;

/*
http://vertx.io/docs/vertx-web/java/#_jwt_authorisation

- issue token
- use created token above to access protected resource
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
        // issue token
        context.response().end(authProvider.generateToken(new JsonObject().put("sub", "paulo"), new JWTOptions()) + "\n");
      }
      else {
        context.fail(401);
      }
    });

    router.route("/protected/*").handler(JWTAuthHandler.create(authProvider));

    router.route("/protected/somepath").handler(context -> {
      final String paulo = context.user().principal().getString("sub");
      System.out.println(paulo);
      context.response().end("paulo accessed "+context.request().uri() + "\n");
    });

    // Configuring required authorities
    final AuthHandler listProductsAuthHandler = RedirectAuthHandler.create(authProvider);
    listProductsAuthHandler.addAuthority("list_products");

    // Need "list_products" authority to list products
    router.route("/listproducts/*").handler(listProductsAuthHandler);

    final AuthHandler settingsAuthHandler = RedirectAuthHandler.create(authProvider);
    settingsAuthHandler.addAuthority("role:admin");

    // Only "admin" has access to /private/settings
    router.route("/private/settings/*").handler(settingsAuthHandler);

    vertx.createHttpServer().requestHandler(router::accept).listen(8080);
  }
}
/*
#issue token
curl -i http://localhost:8080/login/paulo/secret
HTTP/1.1 200 OK
Content-Length: 125

eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXVsbyIsImlhdCI6MTUwNDgwODMxN30.9k_VGqGxj0--rnzB2F1uKNYdwE1WSPj15ePwMuA2M9I

#use created token above to access protected resource
curl -i http://localhost:8080/protected/somepath -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXVsbyIsImlhdCI6MTUwNDgwODMxN30.9k_VGqGxj0--rnzB2F1uKNYdwE1WSPj15ePwMuA2M9I'
HTTP/1.1 200 OK
Content-Length: 20

paulo accessed /protected/somepath
 */

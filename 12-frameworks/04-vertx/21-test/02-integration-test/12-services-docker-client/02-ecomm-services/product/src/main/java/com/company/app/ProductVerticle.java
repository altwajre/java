package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductVerticle extends AbstractVerticle {

  private Map<Integer, Product> products = new LinkedHashMap<>();

  @Override
  public void start(Future<Void> future) {

    prepareData();

    // Create a router object
    Router router = Router.router(vertx);

    // Enable reading of the request body for all routes under "/api/products
    router.route("/api/products").handler(BodyHandler.create());

    // GET all
    router.get("/api/products").handler(this::getAll);

    // POST
    router.post("/api/products").handler(this::addOne);

    // GET
    router.get("/api/products/:id").handler(this::getOne);

    Integer port = config().getInteger("http.port", 8080);
    System.out.println(port);

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(
            // Retrieve the port from the configuration
            // default to 8080
            port,
            ar -> {
              if(ar.succeeded()){
                future.complete();
              }
              else {
                future.fail(ar.cause());
              }
            }
        );
  }

  private void getOne(RoutingContext context) {
    String id = context.request().getParam("id");

    if(id == null) {
      context.response().setStatusCode(400).end();
    }
    else {
      Integer idAsInteger = Integer.valueOf(id);
      Product product = products.get(idAsInteger);
      if(product == null) {
        context.response().setStatusCode(404).end();
      }
      else {
        context.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(product));
      }
    }

  }

  private void addOne(RoutingContext context) {
    // Read the request's content and create an instance of Product
    final Product product = Json.decodeValue(context.getBodyAsString(), Product.class);
    // Add it to the backend map
    products.put(product.getId(), product);

    // Return the created product as JSON
    context.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(product));
  }

  private void getAll(RoutingContext context) {
    context.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(products.values()));
  }

  private void prepareData() {
    Product iPhone = new Product("iPhone");
    products.put(iPhone.getId(), iPhone);
    Product macBook = new Product("MacBook");
    products.put(macBook.getId(), macBook);
  }
}

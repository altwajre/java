package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerVerticle extends AbstractVerticle {
  private Map<Integer, Customer> customers = new LinkedHashMap<>();

  @Override
  public void start(Future<Void> future) {

    createSomeData();

    Router router = Router.router(vertx);

    // Enables the reading of the request body for all routes under "/api/customers"
    router.route("/api/customers*").handler(BodyHandler.create());

    // curl http://localhost:8080/api/customers
    router.get("/api/customers").handler(this::getAll);

    // curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"id": "3", "name": "Harry", "age": "38"}'
    router.post("/api/customers").handler(this::addOne);

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(8080, ar -> {
              if (ar.succeeded()) {
                future.complete();
              } else {
                future.fail(ar.cause());
              }
            }
        );
  }

  private void addOne(RoutingContext context) {
    final Customer customer = Json.decodeValue(context.getBodyAsString(), Customer.class);
    customers.put(customer.getId(), customer);
    context.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json")
        .end(Json.encodePrettily(customer));
  }

  private void getAll(RoutingContext context) {
    context.response()
        .putHeader("content-type", "application/json")
        .end(Json.encodePrettily(customers.values()));
  }

  private void createSomeData() {
    final Customer tom = new Customer(1, "Tom", 18);
    customers.put(tom.getId(), tom);
    final Customer dick = new Customer(2, "Dick", 28);
    customers.put(dick.getId(), dick);
  }

}

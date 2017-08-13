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

    prepareData();

    // Create a router object
    Router router = Router.router(vertx);

    // Enable reading of the request body for all routes under "/api/products
    router.route("/api/customers").handler(BodyHandler.create());

    // GET all
    router.get("/api/customers").handler(this::getAll);

    // POST
    router.post("/api/customers").handler(this::addOne);

    // GET
    router.get("/api/customers/:id").handler(this::getOne);

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
      Customer customer = customers.get(idAsInteger);
      if(customer == null) {
        context.response().setStatusCode(404).end();
      }
      else {
        context.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(customer));
      }
    }

  }

  private void addOne(RoutingContext context) {
    // Read the request's content and create an instance of Customer
    final Customer customer = Json.decodeValue(context.getBodyAsString(), Customer.class);
    // Add it to the backend map
    customers.put(customer.getId(), customer);

    // Return the created customer as JSON
    context.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(customer));
  }

  private void getAll(RoutingContext context) {
    context.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(customers.values()));
  }

  private void prepareData() {
    Customer tom = new Customer("Tom", 18);
    customers.put(tom.getId(), tom);
    Customer dick = new Customer("Dick", 28);
    customers.put(dick.getId(), dick);
  }
}

package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class OfferVerticle extends AbstractVerticle {

  private Map<Integer, Offer> offers = new LinkedHashMap<>();

  @Override
  public void start(Future<Void> future) {

    prepareData();

    // Create a router object
    Router router = Router.router(vertx);

    // Enable reading of the request body for all routes under "/api/offers
    router.route("/api/offers").handler(BodyHandler.create());

    // GET all
    router.get("/api/offers").handler(this::getAll);

    // POST
    router.post("/api/offers").handler(this::addOne);

    // GET
    router.get("/api/offers/:id").handler(this::getOne);

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
              if (ar.succeeded()) {
                future.complete();
              } else {
                future.fail(ar.cause());
              }
            }
        );
  }

  private void getOne(RoutingContext context) {
    String id = context.request().getParam("id");

    if (id == null) {
      context.response().setStatusCode(400).end();
    } else {
      Integer idAsInteger = Integer.valueOf(id);
      Offer offer = offers.get(idAsInteger);
      if (offer == null) {
        context.response().setStatusCode(404).end();
      } else {

        Integer productPort = 8081;

        WebClient.create(vertx)
            .get(productPort, "localhost", "/api/products/1")
            .as(BodyCodec.json(Product.class))
            .send(ar -> {
              if (ar.succeeded()) {
                HttpResponse<Product> response = ar.result();

                Product product = response.body();
                System.out.println("Body:\n" + product.toString());
                offer.setProduct(product);

                // NOTE: put context.response() inside of send() callback
                context.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(Json.encodePrettily(offer));
              } else {
                System.out.println("Error=" + ar.cause());
              }
            });

      }
    }

  }

  private void addOne(RoutingContext context) {
    // Read the request's content and create an instance of Offer
    final Offer offer = Json.decodeValue(context.getBodyAsString(), Offer.class);
    // Add it to the backend map
    offers.put(offer.getId(), offer);

    // Return the created offer as JSON
    context.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(offer));
  }

  private void getAll(RoutingContext context) {
    context.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(offers.values()));
  }

  private void prepareData() {
    Offer phoneOffer = new Offer(1, "phone_offer");
    offers.put(phoneOffer.getId(), phoneOffer);
    Offer laptopOffer = new Offer(2, "laptop_offer");
    offers.put(laptopOffer.getId(), laptopOffer);
  }
}

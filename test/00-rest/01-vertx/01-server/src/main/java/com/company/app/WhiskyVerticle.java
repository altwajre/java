package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class WhiskyVerticle extends AbstractVerticle {

  private Map<Integer, Whisky> products = new LinkedHashMap<>();

  @Override
  public void start(Future<Void> future) {

    createSomeData();

    // Create a router object.
    Router router = Router.router(vertx);

    // Bind "/" to our hello message - so are still compatible.
    router.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response
          .putHeader("content-type", "text/html")
          .end("<h1>Hello from my first Vert.x 3 application</h1>");
    });

    // Serve static resources from the /assets directory
    router.route("/assets/*").handler(StaticHandler.create("assets"));

    // Enables the reading of the request body for all routes under "/api/whiskies".
    router.route("/api/whiskies*").handler(BodyHandler.create());

    // GET all
    // curl http://localhost:8080/api/whiskies
    router.get("/api/whiskies").handler(this::getAll);

    // POST
    // curl -X POST http://localhost:8080/api/whiskies -d '{"name": "Bowmore 18 Years", "origin": "Scotland"}'
    router.post("/api/whiskies").handler(this::addOne);

    // GET one
    // curl http://localhost:8080/api/whiskies/1
    router.get("/api/whiskies/:id").handler(this::getOne);

    // UPDATE
    // curl -X PUT http://localhost:8080/api/whiskies/1 -d '{"name": "Bowmore 18", "origin": "Scotland"}'
    router.put("/api/whiskies/:id").handler(this::updateOne);

    // DELETE
    // curl -X DELETE http://localhost:8080/api/whiskies/1
    router.delete("/api/whiskies/:id").handler(this::deleteOne);

    // Create the HTTP server and pass the "accept" method to the request handler
    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(
            // Retrieve the port from the configuration
            // default to 8080
            config().getInteger("http.port", 8080),
            result -> {
              if(result.succeeded()) {
                future.complete();
              }
              else {
                future.fail(result.cause());
              }
            }
        );
  }

  private void updateOne(RoutingContext routingContext) {

    final String id = routingContext.request().getParam("id");
    JsonObject json = routingContext.getBodyAsJson();

    if(id == null || json == null) {
      routingContext.response().setStatusCode(400).end();
    }
    else {
      final Integer idAsInteger = Integer.valueOf(id);
      Whisky whisky = products.get(idAsInteger);
      if(whisky == null) {
        routingContext.response().setStatusCode(404).end();
      }
      else {
        whisky.setName(json.getString("name"));
        whisky.setOrigin(json.getString("origin"));
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(whisky));
      }
    }

  }

  private void getOne(RoutingContext routingContext) {

    final String id = routingContext.request().getParam("id");
    if(id == null) {
      routingContext.response().setStatusCode(400).end();
    }
    else {
      final Integer idAsInteger = Integer.valueOf(id);
      Whisky whisky = products.get(idAsInteger);
      if(whisky == null) {
        routingContext.response().setStatusCode(404).end();
      }
      else {
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(whisky));
      }
    }
  }

  private void deleteOne(RoutingContext routingContext) {

    final String id = routingContext.request().getParam("id");
    if(id == null){
      routingContext.response().setStatusCode(400).end();
    }
    else {
      Integer idAsInteger = Integer.valueOf(id);
      products.remove(idAsInteger);
    }
    routingContext.response().setStatusCode(204).end();
  }

  private void addOne(RoutingContext routingContext) {

    // read the request's content and create an instance of Whisky.
    final Whisky whisky = Json.decodeValue(routingContext.getBodyAsString(), Whisky.class);
    // Add it to the backend map
    products.put(whisky.getId(), whisky);

    // Return the created whisky as JSON
    routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(whisky));
  }

  private void getAll(RoutingContext routingContext) {
    // Write the HTTP response
    // The response is in JSON using the utf-8 encoding
    // We returns the list of bottles
    routingContext.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(products.values()));
  }

  private void createSomeData() {
    Whisky bowmore = new Whisky("Bowmore 15 Years Laimrig", "Scotland, Islay");
    products.put(bowmore.getId(), bowmore);
    Whisky talisker = new Whisky("Talisker 57° North", "Scotland, Island");
    products.put(talisker.getId(), talisker);
  }
}

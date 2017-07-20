package com.company.app;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.List;
import java.util.stream.Collectors;

public class MainVerticle extends AbstractVerticle {

  private static final String SQL_DROP_CUSTOMERS_TABLE = "DROP TABLE IF EXISTS Customers";
  private static final String SQL_CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS Customers (name VARCHAR (20), age INT);";
  private static final String SQL_CREATE_CUSTOMER = "INSERT INTO Customers (name,age) VALUES (?, ?);";
  private static final String SQL_ALL_CUSTOMERS = "SELECT * FROM Customers;";

  private JDBCClient dbClient;

  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  private Future<Void> prepareDatabase() {
    Future<Void> future = Future.future();

    dbClient = JDBCClient.createShared(vertx, new JsonObject()
        .put("url", "jdbc:hsqldb:hsql://localhost/testdb")
        .put("user", "SA")
        .put("password", "")
        .put("driver_class", "org.hsqldb.jdbcDriver"));

    dbClient.getConnection(ar -> {
      if (ar.failed()) {
        LOGGER.error("Could not open a database connection", ar.cause());
        future.fail(ar.cause());
      } else {
        SQLConnection connection = ar.result();
        connection.execute(SQL_DROP_CUSTOMERS_TABLE, drop -> {
          if(drop.failed()) {
            LOGGER.error("Database preparation drop table error", drop.cause());
            future.fail(drop.cause());
          }
          else {
            connection.execute(SQL_CREATE_CUSTOMERS_TABLE, create -> {
              connection.close();
              if (create.failed()) {
                LOGGER.error("Database preparation create table error", create.cause());
                future.fail(create.cause());
              } else {
                future.complete();
              }
            });
          }
        });
      }
    });

    return future;
  }

  private Future<Void> startHttpServer() {
    System.out.println("#startHttpServer");
    Future<Void> future = Future.future();
    HttpServer server = vertx.createHttpServer();   // <1>

    Router router = Router.router(vertx);   // <2>

    // Bind "/" to our hello message - so are still compatible.
    router.route("/").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      response
          .putHeader("content-type", "text/html")
          .end("<h1>Hello from Vert.x 3</h1>");
    });

    // Enables the reading of the request body for all routes under "/api/customers".
    router.route("/api/customers*").handler(BodyHandler.create());

    // GET all
    // curl http://localhost:8080/api/customers
    router.get("/api/customers").handler(this::getAll);

    // POST
    // curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"name": "Tom", "age": "18"}'
    router.post("/api/customers").handler(this::addOne);

    server
        .requestHandler(router::accept)   // <5>
        .listen(8080, ar -> {   // <6>
          if (ar.succeeded()) {
            LOGGER.info("HTTP server running on port 8080");
            future.complete();
          } else {
            LOGGER.error("Could not start a HTTP server", ar.cause());
            future.fail(ar.cause());
          }
        });

    return future;
  }

  private void addOne(RoutingContext routingContext) {

    // read the request's content and create an instance of Whisky.
    final Customer customer = Json.decodeValue(routingContext.getBodyAsString(), Customer.class);

    JsonArray params = new JsonArray();
    params
        .add(customer.getName())
        .add(customer.getAge());

    dbClient.getConnection(car -> {
      if(car.succeeded()) {
        SQLConnection connection = car.result();
        connection.updateWithParams(SQL_CREATE_CUSTOMER, params, res -> {
          connection.close();
          if(res.succeeded()) {
            routingContext.response()
                .setStatusCode(201)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(customer));
          }
          else {
            routingContext.fail(res.cause());
          }
        });
      }
      else {
        routingContext.fail(car.cause());
      }
    });
  }

  private void getAll(RoutingContext routingContext) {

    dbClient.getConnection(car -> {
      if(car.succeeded()) {
        SQLConnection connection = car.result();
        connection.query(SQL_ALL_CUSTOMERS, res -> {
          connection.close();

          if(res.succeeded()) {
            List<Customer> names = res.result()
                .getResults()
                .stream()
                .map(json -> {
                  String name = json.getString(0);
                  Integer age = json.getInteger(1);
                  return new Customer(name, age);
                })
                .sorted()
                .collect(Collectors.toList());
            System.out.println(names);

            routingContext.response()
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(names));
          }
          else {
            routingContext.fail(res.cause());
          }
        });
      }
      else {
        routingContext.fail(car.cause());
      }
    });
  }

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    Future<Void> steps = prepareDatabase().compose(v -> startHttpServer());
    steps.setHandler(ar -> {
      if(ar.succeeded()) {
        startFuture.complete();
      }
      else {
        startFuture.fail(ar.cause());
      }
    });
  }

}

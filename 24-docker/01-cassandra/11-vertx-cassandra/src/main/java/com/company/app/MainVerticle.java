package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.List;

public class MainVerticle extends AbstractVerticle {

  private static final String SQL_DROP_CUSTOMERS_TABLE = "DROP TABLE IF EXISTS Customer";
  private static final String SQL_CREATE_CUSTOMERS_TABLE = "CREATE TABLE IF NOT EXISTS Customer (name VARCHAR (20), age INT);";
  private static final String SQL_CREATE_CUSTOMER = "INSERT INTO customer (id, name, age) VALUES ('789', 'Dick', '18');";
  private static final String SQL_ALL_CUSTOMERS = "SELECT * FROM Customer;";

  private Session session;
  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  private Future<Void> prepareDatabase() {
    Future<Void> future = Future.future();

    String address = "cassandra-01"; // docker container
//        String address = "127.0.0.1"; // mac
    Cluster cluster = Cluster.builder().addContactPoint(address)
        .build();

    // create session
    session = cluster.connect();

    session.execute("DROP KEYSPACE IF EXISTS testdb;");
    session.execute("CREATE KEYSPACE IF NOT EXISTS testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};");
    session.execute("USE testdb;");
    session.execute("CREATE TABLE IF NOT EXISTS customer (id text PRIMARY KEY, name text, age text);");
    
    future.complete();
    
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

    // Enables the reading of the request body for all routes under "/api/customer".
    router.route("/api/customer*").handler(BodyHandler.create());

    /*
    GET all

    mac:
    curl http://localhost:8080/api/customer

    container:
    curl http://localhost:8081/api/customer
     */
    router.get("/api/customer").handler(this::getAll);

    /*
    POST

    mac:
    curl -X POST http://localhost:8080/api/customer -H 'content-type: application/json' -d '{"name": "Tom", "age": "18"}'

    container:
    curl -X POST http://localhost:8081/api/customer -H 'content-type: application/json' -d '{"name": "Tom", "age": "18"}'
     */
    router.post("/api/customer").handler(this::addOne);

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

    System.out.println("## AddOne");

    // read the request's content and create an instance of Whisky.
    final Customer customer = Json.decodeValue(routingContext.getBodyAsString(), Customer.class);

    JsonArray params = new JsonArray();
    params
        .add(customer.getName())
        .add(customer.getAge());
    
    session.execute(SQL_CREATE_CUSTOMER);

    routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(customer));

  }

  private void getAll(RoutingContext routingContext) {

    ResultSet resultSet = session.execute(SQL_ALL_CUSTOMERS);

    MappingManager manager = new MappingManager(session);
    Mapper<Customer> mapper = manager.mapper(Customer.class);
    Result<Customer> customers = mapper.map(resultSet);
    List<Customer> list = customers.all();

    routingContext.response()
        .putHeader("content-type", "application/json; charset=utf-8")
        .end(Json.encodePrettily(list));

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

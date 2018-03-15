package com.company.app;

import com.company.app.dao.CustomerJpaDao;
import com.company.app.model.Customer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomerVerticle extends AbstractVerticle {
  EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-unit");
  EntityManager entityManager = entityManagerFactory.createEntityManager();

  @Override
  public void start(Future<Void> future) {

    Router router = Router.router(vertx);

    // Enables the reading of the request body for all routes under "/api/customers"
    router.route("/api/customers*").handler(BodyHandler.create());

    // curl http://localhost:8080/api/customers
    router.get("/api/customers").handler(this::getAll);

    // curl http://localhost:8080/api/customers/1
    router.get("/api/customers/:id").handler(this::getOne);

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

  private void getOne(RoutingContext routingContext) {
    final String id = routingContext.request().getParam("id");
    if (id == null) {
      routingContext.response().setStatusCode(400).end();
    } else {
      final Long idAsLong = Long.valueOf(id);

      entityManager.getTransaction().begin();

      CustomerJpaDao customerDao = new CustomerJpaDao();
      customerDao.setEntityManager(entityManager);
      Customer customer = customerDao.findById(idAsLong);

      entityManager.getTransaction().commit();

      if (customer == null) {
        routingContext.response().setStatusCode(404).end();
      } else {
        routingContext.response()
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(customer));
      }

    }

  }

  private void addOne(RoutingContext context) {
    final Customer customer = Json.decodeValue(context.getBodyAsString(), Customer.class);

    entityManager.getTransaction().begin();

    CustomerJpaDao customerDao = new CustomerJpaDao();
    customerDao.setEntityManager(entityManager);
    Customer createdCustomer = customerDao.save(customer);

    entityManager.getTransaction().commit();


    context.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json")
        .end(Json.encodePrettily(createdCustomer));
  }

  private void getAll(RoutingContext context) {
    context.response()
        .putHeader("content-type", "application/json")
        .end("get all");
  }

}

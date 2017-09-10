package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(VertxUnitRunner.class)
public class CustomerVerticleTest {
  private Vertx vertx;
  private Integer port = 8080;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    vertx.deployVerticle(new CustomerVerticle(), context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void nestedCallbacks(TestContext context) {
    final Async async = context.async();
    final HttpClient httpClient = vertx.createHttpClient();

    final String json = Json.encodePrettily(new Customer(3, "Harry", 38));

    System.out.println("POST");
    // POST
    httpClient
        .post(port, "localhost", "/api/customers")
        .putHeader("content-type", "application/json")
        .putHeader("content-length", Integer.toString(json.length()))
        .handler(resp1 -> {
          context.assertEquals(201, resp1.statusCode());
          resp1.bodyHandler(body1 -> {
            System.out.println(body1);

            System.out.println("GET");
            // GET
            httpClient.get(port, "localhost", "/api/customers", resp2 -> {
              context.assertEquals(200, resp2.statusCode());
              resp2.bodyHandler(body2 -> {
                System.out.println(body2);
                async.complete();
              });
            }).end();
          });
        })
        .write(json)
        .end();
  }

  @Test
  public void countDownLatchBlocksEachClientCall() throws Exception {
    final String json = Json.encodePrettily(new Customer(3, "Harry", 38));

    final CountDownLatch latch1 = new CountDownLatch(1);

    System.out.println("POST");
    // POST
    vertx.createHttpClient()
        .post(port, "localhost", "/api/customers")
        .putHeader("content-type", "application/json")
        .putHeader("content-length", Integer.toString(json.length()))
        .handler(resp1 -> {
          assertEquals(201, resp1.statusCode());
          resp1.bodyHandler(body1 -> {
            System.out.println(body1);
            latch1.countDown();
          });
        })
        .write(json)
        .end();
    latch1.await();

    final CountDownLatch latch2 = new CountDownLatch(1);

    System.out.println("GET");
    // GET
    vertx.createHttpClient().get(port, "localhost", "/api/customers", resp2 -> {
      assertEquals(200, resp2.statusCode());
      resp2.bodyHandler(body2 -> {
        System.out.println(body2);
        latch2.countDown();
      });
    }).end();
    latch2.await();
  }
/*
POST
{
  "id" : 3,
  "name" : "Harry",
  "age" : 38
}
GET
[ {
  "id" : 1,
  "name" : "Tom",
  "age" : 18
}, {
  "id" : 2,
  "name" : "Dick",
  "age" : 28
}, {
  "id" : 3,
  "name" : "Harry",
  "age" : 38
} ]
 */
}

package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(VertxUnitRunner.class)
public class CustomerVerticleTest {
  private Vertx vertx = Vertx.vertx();
  private Integer port = 8080;

  @Before
  public void setUp(TestContext context) {
    vertx.deployVerticle(new CustomerVerticle(), context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void webClientHelper(TestContext context) throws IOException {
    final Async async = context.async();

    final String json = Json.encodePrettily(new Customer(5, "Sam", 58));
    ObjectMapper mapper = new ObjectMapper();
    final JsonNode root = mapper.readTree(json);
    final String prettyJson = Json.encodePrettily(root);
    System.out.println("prettyJson: " + prettyJson);


    System.out.println("POST");
    // POST
    String uri = "http://localhost:" + port + "/api/customers";

    WebClientHelper<Customer> client = new WebClientHelper<>(Customer.class);
    client.post(uri, root, ar1 -> {
      if(ar1.succeeded()) {
        final Customer resp1 = ar1.result();
        System.out.println(resp1);
        client.getList(uri, ar2 -> {
          final List<Customer> customers = ar2.result();
          System.out.println(customers);
          async.complete();
        });
      }
      else {
        System.out.println("Error: " + ar1.cause());
      }
    });
  }
/*
prettyJson: {
  "id" : 5,
  "name" : "Sam",
  "age" : 58
}
POST
Customer(id=5, name=Sam, age=58)
[Customer(id=1, name=Tom, age=18), Customer(id=2, name=Dick, age=28), Customer(id=5, name=Sam, age=58)]
 */

  @Test
  public void webClientAbsSendBuffer(TestContext context) throws IOException {
    final Async async = context.async();
    WebClient client = WebClient.create(vertx);

    final String json = Json.encodePrettily(new Customer(4, "Will", 48));
    ObjectMapper mapper = new ObjectMapper();
    final JsonNode root = mapper.readTree(json);

    final String prettyJson = Json.encodePrettily(root);

    System.out.println("POST");
    // POST
    String uri = "http://localhost:" + port + "/api/customers";
    client
        .postAbs(uri)
        .sendBuffer(Buffer.buffer(prettyJson), ar1 -> {
          if (ar1.succeeded()) {
            final HttpResponse<Buffer> resp1 = ar1.result();
            System.out.println(resp1.statusCode());
            System.out.println(resp1.body());

            System.out.println("GET all");
            // Get all
            client
                .getAbs(uri)
                .send(ar2 -> {
                  if (ar2.succeeded()) {
                    final HttpResponse<Buffer> resp2 = ar2.result();
                    System.out.println(resp2.statusCode());
                    System.out.println(resp2.body());
                  } else {
                    System.out.println("Error: " + ar2.cause());
                  }
                  async.complete();
                });

          } else {
            System.out.println("Error: " + ar1.cause());
          }
        });
  }
/*
POST
201
{
  "id" : 4,
  "name" : "Will",
  "age" : 48
}
GET all
200
[ {
  "id" : 1,
  "name" : "Tom",
  "age" : 18
}, {
  "id" : 2,
  "name" : "Dick",
  "age" : 28
}, {
  "id" : 4,
  "name" : "Will",
  "age" : 48
} ]
 */

  @Test
  public void webClientSendBuffer(TestContext context) throws IOException {
    final Async async = context.async();
    WebClient client = WebClient.create(vertx);

    final String json = Json.encodePrettily(new Customer(4, "Will", 48));
    ObjectMapper mapper = new ObjectMapper();
    final JsonNode root = mapper.readTree(json);

    final String reqJson = Json.encodePrettily(root);

    System.out.println("POST");
    // POST
    client
        .post(port, "localhost", "/api/customers")
        .sendBuffer(Buffer.buffer(reqJson), ar1 -> {
          if (ar1.succeeded()) {
            final HttpResponse<Buffer> resp1 = ar1.result();
            System.out.println(resp1.statusCode());
            System.out.println(resp1.body());

            System.out.println("GET all");
            // Get all
            client
                .get(port, "localhost", "/api/customers")
                .send(ar2 -> {
                  if (ar2.succeeded()) {
                    final HttpResponse<Buffer> resp2 = ar2.result();
                    System.out.println(resp2.statusCode());
                    System.out.println(resp2.body());
                  } else {
                    System.out.println("Error: " + ar2.cause());
                  }
                  async.complete();
                });

          } else {
            System.out.println("Error: " + ar1.cause());
          }
        });
  }

  @Test
  public void webClient(TestContext context) {
    final Async async = context.async();
    WebClient client = WebClient.create(vertx);

    System.out.println("POST");
    // POST
    client
        .post(port, "localhost", "/api/customers")
        .sendJson(new Customer(4, "Will", 48), ar1 -> {
          if (ar1.succeeded()) {
            final HttpResponse<Buffer> resp1 = ar1.result();
            System.out.println(resp1.statusCode());
            System.out.println(resp1.body());

            System.out.println("GET all");
            // Get all
            client
                .get(port, "localhost", "/api/customers")
                .send(ar2 -> {
                  if (ar2.succeeded()) {
                    final HttpResponse<Buffer> resp2 = ar2.result();
                    System.out.println(resp2.statusCode());
                    System.out.println(resp2.body());
                  } else {
                    System.out.println("Error: " + ar2.cause());
                  }
                  async.complete();
                });

          } else {
            System.out.println("Error: " + ar1.cause());
          }
        });
  }

  @Test
  public void httpClient(TestContext context) {
    final Async async = context.async();
    final HttpClient client = vertx.createHttpClient();

    final String json = Json.encodePrettily(new Customer(3, "Harry", 38));

    System.out.println("POST");
    // POST
    client
        .post(port, "localhost", "/api/customers")
        .putHeader("content-type", "application/json")
        .putHeader("content-length", Integer.toString(json.length()))
        .handler(resp1 -> {
          context.assertEquals(201, resp1.statusCode());
          resp1.bodyHandler(body1 -> {
            System.out.println(body1);

            System.out.println("GET all");
            // GET all
            client.get(port, "localhost", "/api/customers", resp2 -> {
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
}
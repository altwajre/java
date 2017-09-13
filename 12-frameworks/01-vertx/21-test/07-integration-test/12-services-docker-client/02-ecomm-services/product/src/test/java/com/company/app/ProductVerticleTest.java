package com.company.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class ProductVerticleTest {

  private Vertx vertx;
  private Integer port = 8081;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();

    JsonObject config = new JsonObject()
        .put("http.port", port);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    vertx.deployVerticle(new ProductVerticle(), options, context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testGetAll(TestContext context) {
    Async async = context.async();

    System.out.println("# port: " + port);

    WebClient.create(vertx)
        .get(port, "localhost", "/api/products")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();

            Buffer body = response.body();
            System.out.println("Body:\n" + body.toString());

            // convert JsonString to JsonArray
            JsonArray array = new JsonArray(body.toString());
            System.out.println("JsonArray: " + array);

            // convert each json to pojo
            array.forEach(j -> {
              Product product = ((JsonObject) j).mapTo(Product.class);
              System.out.println("Product POJO: " + product);
            });
          } else {
            System.out.println("Error=" + ar.cause());
          }
          async.complete();
        });
  }

  @Test
  public void testGetOne(TestContext context) {
    Async async = context.async();

    WebClient.create(vertx)
        .get(port, "localhost", "/api/products/1")
        .as(BodyCodec.json(Product.class))
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Product> response = ar.result();

            Product body = response.body();
            System.out.println("Body:\n" + body.toString());

          } else {
            System.out.println("Error=" + ar.cause());
          }
          async.complete();
        });

  }

  @Test
  public void testPostOne(TestContext context) {
    Async async = context.async();

    WebClient.create(vertx)
        .post(port, "localhost", "/api/products")
        .as(BodyCodec.json(Product.class))
        .sendJson(new Product(4, "new_Product"), ar -> {
          if (ar.succeeded()) {
            HttpResponse<Product> response = ar.result();

            Product body = response.body();
            System.out.println("Body:\n" + body.toString());

          } else {
            System.out.println("Error=" + ar.cause());
          }
        });

    vertx.setTimer(800, l -> {
      WebClient.create(vertx)
          .get(port, "localhost", "/api/products")
          .send(ar -> {
            if (ar.succeeded()) {
              HttpResponse<Buffer> response = ar.result();

              Buffer body = response.body();
              System.out.println("Body:\n" + body.toString());

              // convert JsonString to JsonArray
              JsonArray array = new JsonArray(body.toString());
              System.out.println("JsonArray: " + array);

              // convert each json to pojo
              array.forEach(j -> {
                Product product = ((JsonObject) j).mapTo(Product.class);
                System.out.println("Product POJO: " + product);
              });
            } else {
              System.out.println("Error=" + ar.cause());
            }
            async.complete();
          });
    });
  }

  @Test
  public void testDeleteOne(TestContext context) {
    Async async = context.async();

    WebClient.create(vertx)
        .delete(port, "localhost", "/api/products/2")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            System.out.println(response.statusCode());

          } else {
            System.out.println("Error=" + ar.cause());
          }
        });

    vertx.setTimer(800, l -> {
      WebClient.create(vertx)
          .get(port, "localhost", "/api/products")
          .send(ar -> {
            if (ar.succeeded()) {
              HttpResponse<Buffer> response = ar.result();

              Buffer body = response.body();
              System.out.println("Body:\n" + body.toString());

              // convert JsonString to JsonArray
              JsonArray array = new JsonArray(body.toString());
              System.out.println("JsonArray: " + array);

              // convert each json to pojo
              array.forEach(j -> {
                Product product = ((JsonObject) j).mapTo(Product.class);
                System.out.println("Product POJO: " + product);
              });
            } else {
              System.out.println("Error=" + ar.cause());
            }
            async.complete();
          });
    });

  }
}

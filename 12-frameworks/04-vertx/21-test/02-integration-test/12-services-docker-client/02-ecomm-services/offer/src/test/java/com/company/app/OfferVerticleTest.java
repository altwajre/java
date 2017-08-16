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
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class OfferVerticleTest {

  private Vertx vertx;
  private Integer port = 8082;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();

    JsonObject config = new JsonObject()
        .put("http.port", port);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    vertx.deployVerticle(new OfferVerticle(), options, context.asyncAssertSuccess());
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
        .get(port, "localhost", "/api/offers")
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
              Offer offer = ((JsonObject) j).mapTo(Offer.class);
              System.out.println("Offer POJO: " + offer);
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
        .get(port, "localhost", "/api/offers/1")
        .as(BodyCodec.json(Offer.class))
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Offer> response = ar.result();

            Offer body = response.body();
            System.out.println("Body:\n" + body.toString());

          } else {
            System.out.println("Error=" + ar.cause());
          }
          async.complete();
        });

  }

}

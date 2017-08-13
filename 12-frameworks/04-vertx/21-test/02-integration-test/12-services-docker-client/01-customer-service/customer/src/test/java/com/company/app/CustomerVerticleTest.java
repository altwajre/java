package com.company.app;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class CustomerVerticleTest {

  private Vertx vertx;
  private Integer port = 8081;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();

    JsonObject config = new JsonObject()
        .put("http.port", port);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);

    Vertx.vertx().deployVerticle(new CustomerVerticle(), options, context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testGetAll(TestContext context) {
    Async async = context.async();

    vertx.createHttpClient().getNow(port, "localhost", "/api/customers", response -> {
      response.handler(body -> {
        System.out.println(body.toString());
        context.assertTrue(body.toString().contains("Tom"));
        async.complete();
      });
    });
  }

}

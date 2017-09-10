package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(VertxUnitRunner.class)
public class MyVerticleTest {
  private Vertx vertx;
  private Integer port = 8080;

  @Before
  public void setUp(TestContext context) {
    vertx = Vertx.vertx();
    this.vertx.deployVerticle(new MyVerticle(), context.asyncAssertSuccess());
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  @Test
  public void testPathParameters(TestContext context) {
    final Async async = context.async();

    vertx.createHttpClient().post(port, "localhost", "/catalogue/products/tools/drill123", resp -> {
      context.assertEquals(200, resp.statusCode());
      resp.bodyHandler(body -> {
        System.out.println(body);
      });
      async.complete();
    }).end();
  }

}
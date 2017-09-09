package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

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
  public void nestedCallbacks(TestContext context) {
    final Async async = context.async();

    final HttpClient httpClient = vertx.createHttpClient();

    // issue token
    httpClient.get(port, "localhost", "/login/paulo/secret", resp1 -> {
      System.out.println(resp1.statusCode());
      context.assertEquals(200, resp1.statusCode());
      resp1.bodyHandler(body1 -> {
        String id = body1.toString();
        System.out.println(id);

        // use token to access protected resource
        httpClient.get(port, "localhost", "/protected/somepath", resp2 -> {
          System.out.println(resp2.statusCode());
          resp2.bodyHandler(body2 -> {
            System.out.println(body2);
            async.complete();
          });
        }).putHeader("Authorization", "Bearer " + id).end();

      });
    }).end();
  }

  /*
  When using CountDownLatch, don't use context.async() anymore
   */
  @Test
  public void countDownLatchBlocksEachClientCall() throws Exception {
//    final Async async = context.async();

    final String[] id = new String[1];

    final CountDownLatch latch1 = new CountDownLatch(1);

    // issue toekn
    vertx.createHttpClient().get(port, "localhost", "/login/paulo/secret", resp1 -> {
      assertEquals(200, resp1.statusCode());
      resp1.bodyHandler(body1 -> {
        id[0] = body1.toString();
        System.out.println(id[0]);
        latch1.countDown();
      });
    }).end();

    latch1.await();

    final CountDownLatch latch2 = new CountDownLatch(1);

    // use token to access protected resource
    vertx.createHttpClient().get(port, "localhost", "/protected/somepath", resp2 -> {
      assertEquals(200, resp2.statusCode());
      resp2.bodyHandler(body2 -> {
        System.out.println(body2);
        latch2.countDown();
//        async.complete();
      });
    }).putHeader("Authorization", "Bearer " + id[0]).end();

    latch2.await();
  }
}

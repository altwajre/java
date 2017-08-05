package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.Repeat;
import io.vertx.ext.unit.junit.RepeatRule;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/*
Repeating a test
When a test fails randomly or not often, for instance a race condition, it is convenient to run the same test multiple
times to increase the likelihood failure of the test.

With JUnit a test has to be annotated with @Repeat to be repeated. The test must also define the RepeatRule among its
rules.

Repeating a test with JUnit
@RunWith(VertxUnitRunner.class)
public class RepeatingTest {

  @Rule
  public RepeatRule rule = new RepeatRule();

  @Repeat(1000)
  @Test
  public void testSomething(TestContext context) {
    // This will be executed 1000 times
  }
}

NOTE
test repetition are executed sequentially
 */
@RunWith(VertxUnitRunner.class)
public class RepeatingTest {
  private String expected = "Hello from server";
  private boolean isDeployed = false;

  private Vertx vertx;

  @Rule
  public RepeatRule rule = new RepeatRule();

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

    String host = context.get("host");
    System.out.println(Thread.currentThread().getName() + ": Before - host=" + host);

    if(!isDeployed){
      vertx = Vertx.vertx();

      vertx.deployVerticle(HelloVerticle.class.getName(),
          context.asyncAssertSuccess());
      isDeployed = true;
    }

  }

  @Repeat(3)
  @Test
  public void webClientCallHelloVerticle(TestContext context) {

    String host = context.get("host");
    System.out.println(Thread.currentThread().getName() + ": Test - host=" + host);

    Async async = context.async();

    WebClient client = WebClient.create(vertx);

    client
        .get(8080, host, "/")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();

            context.assertEquals(200, response.statusCode());

            String body = response.body().toString();
            System.out.println(Thread.currentThread().getName() + ": send() callback - body=" + body);
            context.assertEquals(expected, body);

            async.complete();
          } else {

            context.fail(ar.cause());
          }
        });
  }

}
/*
*** Iteration 1/3 of test webClientCallHelloVerticle(com.company.app.RepeatingTest)
main: Before - host=localhost
main: Test - host=localhost
vert.x-eventloop-thread-2: send() callback - body=Hello from server
*** Iteration 2/3 of test webClientCallHelloVerticle(com.company.app.RepeatingTest)
main: Before - host=localhost
main: Test - host=localhost
vert.x-eventloop-thread-3: send() callback - body=Hello from server
*** Iteration 3/3 of test webClientCallHelloVerticle(com.company.app.RepeatingTest)
main: Before - host=localhost
main: Test - host=localhost
vert.x-eventloop-thread-4: send() callback - body=Hello from server
 */

package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import org.junit.*;
import org.junit.runner.RunWith;

/*
Running a test on a Vert.x context
By default the thread invoking the test methods is the JUnit thread. The RunTestOnContext JUnit rule can be used to
alter this behavior for running these test methods with a Vert.x event loop thread.

Thus there must be some care when state is shared between test methods and Vert.x handlers as they won’t be on the same
thread, e.g incrementing a counter in a Vert.x handler and asserting the counter in the test method. One way to solve
this is to use proper synchronization, another is to execute test methods on a Vert.x context that will be propagated
to the created handlers.

For this purpose the RunTestOnContext rule needs a Vertx instance. Such instance can be provided, otherwise the rule
will manage an instance under the hood. Such instance can be retrieved when the test is running, making this rule a way
to manage a Vertx instance as well.

Run a Java class as a JUnit test suite
@RunWith(VertxUnitRunner.class)
public class RunOnContextJUnitTestSuite {

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Test
  public void testSomething(TestContext context) {
    // Use the underlying vertx instance
    Vertx vertx = rule.vertx();
  }
}


WARNING
keep in mind that you cannot block the event loop when using this rule.
Usage of classes like CountDownLatch or similar classes must be done with care.
 */
@RunWith(VertxUnitRunner.class)
public class RunOnVertxEventLoopTest {
  private String expected = "Hello from server";
  private Vertx vertx;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @BeforeClass
  public static void setUpClass(TestContext context) {
    context.put("host", "localhost");
  }

  @Before
  public void setUp(TestContext context) {

    String host = context.get("host");
    System.out.println(Thread.currentThread().getName() + ": Before - host=" + host);

    vertx = rule.vertx();

    // Register the context exception handler
    vertx.exceptionHandler(context.exceptionHandler());

    vertx.deployVerticle(HelloVerticle.class.getName(),
        context.asyncAssertSuccess());

  }

  @After
  public void after(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

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
vert.x-eventloop-thread-0: Before - host=localhost
vert.x-eventloop-thread-0: Test - host=localhost
vert.x-eventloop-thread-0: send() callback - body=Hello from server
 */

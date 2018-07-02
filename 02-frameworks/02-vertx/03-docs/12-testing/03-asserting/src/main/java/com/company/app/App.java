package com.company.app;

import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;

/*
http://vertx.io/docs/vertx-unit/java/#_asserting
 */
public class App {
  public static void main(String[] args) {
    TestSuite suite = TestSuite.create("the_test_suite");

    suite.test("assertEquals", testContext -> {
      String expected = "1";
      String actual = "1";
      testContext.assertEquals(expected, actual, "Should have been " + expected + " instead of " + actual);
    });

    suite.test("assertNotEquals", testContext -> {
      String expected = "1";
      String actual = "2";
      testContext.assertNotEquals(expected, actual);
    });

    suite.test("assertNull", testContext -> {
      String expected = null;
      testContext.assertNull(expected);
    });

    suite.test("assertNull", testContext -> {
      String expected = "1";
      testContext.assertNotNull(expected);
    });

    suite.test("assertTrue", testContext -> {
      Boolean expected = true;
      testContext.assertTrue(expected);
    });

    suite.test("assertFalse", testContext -> {
      Boolean expected = false;
      testContext.assertFalse(expected);
    });

    suite.test("fail", testContext -> {
      testContext.fail("That should never happen");
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
}
/*
Begin test suite the_test_suite
Begin test assertEquals
Passed assertEquals
Begin test assertNotEquals
Passed assertNotEquals
Begin test assertNull
Passed assertNull
Begin test assertNull
Passed assertNull
Begin test assertTrue
Passed assertTrue
Begin test assertFalse
Passed assertFalse
Begin test fail
Failed fail
java.lang.AssertionError: That should never happen
	at io.vertx.ext.unit.impl.TestContextImpl.reportAssertionError(TestContextImpl.java:432)
	at io.vertx.ext.unit.impl.TestContextImpl.fail(TestContextImpl.java:310)
	at com.company.app.App.lambda$main$6(App.java:44)
 */

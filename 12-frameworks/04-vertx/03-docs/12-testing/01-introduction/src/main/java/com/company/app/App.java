package com.company.app;

import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;

/*
http://vertx.io/docs/vertx-unit/java/#_introduction

 */
public class App {
  public static void main(String[] args) {
    TestSuite suite = TestSuite.create("the_test_suite");

    suite.test("test_case_pass", testContext -> {
      String expected = "value";
      String actual = "value";
      testContext.assertEquals(expected, actual);
    });

    suite.test("test_case_fail", testContext -> {
      String expected = "value";
      String actual = "value_x";
      testContext.assertEquals(expected, actual);
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
}
/*
Begin test suite the_test_suite
Begin test test_case_pass
Passed test_case_pass
Begin test test_case_fail
End test suite the_test_suite , run: 2, Failures: 1, Errors: 0
Failed test_case_fail
java.lang.AssertionError: Not equals : value != value_x
	at io.vertx.ext.unit.impl.TestContextImpl.reportAssertionError(TestContextImpl.java:432)
	at io.vertx.ext.unit.impl.TestContextImpl.assertEquals(TestContextImpl.java:338)
	at io.vertx.ext.unit.impl.TestContextImpl.assertEquals(TestContextImpl.java:325)
	at com.company.app.App.lambda$main$1(App.java:21)
 */

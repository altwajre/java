package com.company.app;

import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;
import org.junit.Assert;
import org.junit.Test;

/*
http://vertx.io/docs/vertx-unit/java/#_introduction

 */
public class AppTest {

  @Test
  public void runTest() {

//    Assert.assertEquals("", "");
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
Failed test_case_fail
java.lang.AssertionError: Not equals : value != value_x
	at io.vertx.ext.unit.impl.TestContextImpl.reportAssertionError(TestContextImpl.java:432)
	at io.vertx.ext.unit.impl.TestContextImpl.assertEquals(TestContextImpl.java:338)
	at io.vertx.ext.unit.impl.TestContextImpl.assertEquals(TestContextImpl.java:325)
	at com.company.app.AppTest.lambda$runTest$1(AppTest.java:26)
 */

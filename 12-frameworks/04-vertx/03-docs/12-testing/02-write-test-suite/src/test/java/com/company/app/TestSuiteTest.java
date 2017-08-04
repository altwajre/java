package com.company.app;

import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.TestSuite;
import io.vertx.ext.unit.report.ReportOptions;
import org.junit.Test;

/*
http://vertx.io/docs/vertx-unit/java/#_writing_a_test_suite

The test cases declaration order is not guaranteed, so test cases should not rely on the execution of another
test case to run. Such practice is considered as a bad one.

 */
public class TestSuiteTest {

  @Test
  public void runTest() {

//    Assert.assertEquals("expected", "actual");
    TestSuite suite = TestSuite.create("the_test_suite");

    suite.test("test_case_1", testContext -> {
      System.out.println(Thread.currentThread().getName());
      String expected = "1";
      String actual = "1";
      testContext.assertEquals(expected, actual);
    });

    suite.test("test_case_2", testContext -> {
      System.out.println(Thread.currentThread().getName());
      String expected = "2";
      String actual = "2";
      testContext.assertEquals(expected, actual);
    });

    suite.test("test_case_3", testContext -> {
      System.out.println(Thread.currentThread().getName());
      String expected = "3";
      String actual = "3";
      testContext.assertEquals(expected, actual);
    });

    suite.run(
        new TestOptions()
            .addReporter(
                new ReportOptions()
                    .setTo("console")));
  }
/*
Begin test suite the_test_suite
Begin test test_case_1
Passed test_case_1
Begin test test_case_2
Passed test_case_2
Begin test test_case_3
Passed test_case_3
End test suite the_test_suite , run: 3, Failures: 0, Errors: 0
 */

  @Test
  public void chainedTest() {

    TestSuite suite = TestSuite.create("chained_test_suite");

    suite.test("test_case_1", testContext -> {
      String expected = "1";
      String actual = "1";
      testContext.assertEquals(expected, actual);
    }).test("test_case_2", testContext -> {
      String expected = "2";
      String actual = "2";
      testContext.assertEquals(expected, actual);
    }).test("test_case_3", testContext -> {
      String expected = "3";
      String actual = "3";
      testContext.assertEquals(expected, actual);
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite chained_test_suite
Begin test test_case_1
Passed test_case_1
Begin test test_case_2
Passed test_case_2
Begin test test_case_3
Passed test_case_3
End test suite the_test_suite , run: 3, Failures: 0, Errors: 0
 */

/*
The before callback is executed before any tests, when it fails, the test suite execution will stop and the failure is
reported. The after callback is the last callback executed by the testsuite, unless the before callback reporter a failure.
 */
  @Test
  public void beforeAfterTest() {

    TestSuite suite = TestSuite.create("before_after_test_suite");

    suite.before(testContext -> {
      System.out.println("#before");
    }).test("test_case_1", testContext -> {
      String expected = "1";
      String actual = "1";
      testContext.assertEquals(expected, actual);
    }).test("test_case_2", testContext -> {
      String expected = "2";
      String actual = "2";
      testContext.assertEquals(expected, actual);
    }).test("test_case_3", testContext -> {
      String expected = "3";
      String actual = "3";
      testContext.assertEquals(expected, actual);
    }).after(testContext -> {
      System.out.println("#after");
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite before_after_test_suite
#before
Begin test test_case_1
Passed test_case_1
Begin test test_case_2
Passed test_case_2
Begin test test_case_3
Passed test_case_3
#after
End test suite before_after_test_suite , run: 3, Failures: 0, Errors: 0
 */

/*
The beforeEach callback is executed before each test case, when it fails, the test case is not executed and the failure
is reported. The afterEach callback is the executed just after the test case callback, unless the beforeEach callback
reported a failure.
 */
  @Test
  public void beforeEachAfterEachTest() {

    TestSuite suite = TestSuite.create("beforeEach_afterEach_test_suite");

    suite.beforeEach(testContext -> {
      System.out.println("#beforeEach");
    }).test("test_case_1", testContext -> {
      String expected = "1";
      String actual = "1";
      testContext.assertEquals(expected, actual);
    }).test("test_case_2", testContext -> {
      String expected = "2";
      String actual = "2";
      testContext.assertEquals(expected, actual);
    }).test("test_case_3", testContext -> {
      String expected = "3";
      String actual = "3";
      testContext.assertEquals(expected, actual);
    }).afterEach(testContext -> {
      System.out.println("#afterEach");
    });

    suite.run(new TestOptions().addReporter(new ReportOptions().setTo("console")));
  }
/*
Begin test suite beforeEach_afterEach_test_suite
Begin test test_case_1
#beforeEach
#afterEach
Passed test_case_1
Begin test test_case_2
#beforeEach
#afterEach
Passed test_case_2
Begin test test_case_3
#beforeEach
#afterEach
Passed test_case_3
End test suite beforeEach_afterEach_test_suite , run: 3, Failures: 0, Errors: 0
 */

}
package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.experimental.results.PrintableResult.testResult;
import static org.junit.experimental.results.ResultMatchers.isSuccessful;

/*
ExternalResource is a base class for Rules (like TemporaryFolder) that set up an external resource before a test
(a file, socket, server, database connection, etc.), and guarantee to tear it down afterward:
 */
public class B_ExternalResourceTest {

  private static String callSequence;

  public static class UsesExternalResource {

    @Rule
    public ExternalResource resource = new ExternalResource() {
      @Override
      protected void before() throws Throwable {
        callSequence += "before ";
      }

      @Override
      protected void after() {
        callSequence += "after ";
      }
    };

    @Test
    public void test1() {
      callSequence += "test_1 ";
    }

    @Test
    public void test2() {
      callSequence += "test_2 ";
    }
  }

  @Test
  public void externalResourceGeneratesCorrectSequence() {

    callSequence = "";
    String expectedCallSequence = "before test_1 after before test_2 after ";
    assertThat(testResult(UsesExternalResource.class), isSuccessful());
    assertEquals(expectedCallSequence, callSequence);
  }

  public static class TwoExternalResources {

    @Rule
    public ExternalResource resource1 = new ExternalResource() {
      @Override
      protected void before() throws Throwable {
        System.out.println("resource1.before()");
      }

      @Override
      protected void after() {
        System.out.println("resource1.after()");
      }
    };

    @Rule
    public ExternalResource resource2 = new ExternalResource() {
      @Override
      protected void before() throws Throwable {
        System.out.println("resource2.before()");
      }

      @Override
      protected void after() {
        System.out.println("resource2.after()");
      }
    };

    @Test
    public void test1() {
      System.out.println("test1()");
    }
  }

  @Test
  public void twoExternalResourcesTest() {
    testResult(TwoExternalResources.class);
//    assertThat(testResult(TwoExternalResources.class), isSuccessful());
  }

  @Test
  public void shouldThrowMultipleFailureExceptionWhenTestFailsAndClosingResourceFails() throws Throwable {
    // given
    ExternalResource resourceRule = new ExternalResource() {
      @Override
      protected void after() {
        throw new RuntimeException("simulating resource tear down failure");
      }
    };
    Statement failingTest = new Fail(new RuntimeException("simulated test failure"));
    Description dummyDescription = Description.createTestDescription(
        "dummy test class name", "dummy test name");

    try {
      resourceRule.apply(failingTest, dummyDescription).evaluate();
      fail("ExternalResource should throw");
    } catch (MultipleFailureException e) {
      assertThat(e.getMessage(), allOf(
          containsString("simulated test failure"),
          containsString("simulating resource tear down failure")
      ));
    }
  }

}

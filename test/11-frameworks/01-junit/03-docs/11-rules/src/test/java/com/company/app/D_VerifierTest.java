package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Verifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.experimental.results.PrintableResult.testResult;
import static org.junit.experimental.results.ResultMatchers.isSuccessful;

/*
Verifier is a base class for Rules like ErrorCollector, which can turn otherwise passing test methods into failing tests
if a verification check is failed.
 */
public class D_VerifierTest {

  private static String sequence;

  public static class UsesVerifier {

    @Rule
    public Verifier collector = new Verifier() {
      @Override
      protected void verify() {
        sequence += "verify ";
      }
    };

    @Test
    public void test() {
      sequence += "test ";
    }
  }


  @Test
  public void verifierRunsAfterTest() {
    sequence = "";
    assertThat(testResult(UsesVerifier.class), isSuccessful());
    assertEquals("test verify ", sequence);
  }

  public static class ThrowExceptionVerifier {

    public Verifier verifier = new Verifier() {
      @Override
      protected void verify() throws Throwable {
        throw new RuntimeException();
      }

      @Test
      public void test() {
        System.out.println("test() ");
      }
    };
  }

  @Test
  public void testThrowExceptionVerifier() {
    assertThat(testResult(ThrowExceptionVerifier.class), isSuccessful());
  }
  /*
  output:
  java.lang.AssertionError:
  Expected: has 0 failures
       but: was <
  Time: 0.019
  There was 1 failure:
   */

}

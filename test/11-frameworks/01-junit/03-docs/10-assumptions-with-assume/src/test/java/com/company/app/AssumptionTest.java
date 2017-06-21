package com.company.app;

import org.junit.AssumptionViolatedException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;
import static org.junit.Assume.assumeThat;

public class AssumptionTest {

  @Test
  public void assumeThatPasses() {
    assumeThat(1, is(1));
  }

  @Test
  public void assumeThatPassesOnStrings() {
    assumeThat("x", is("x"));
  }

  @Test
  public void assumeNotNullThrowsException() {

    Object[] objects = {1, 2, null};

    try{
      assumeNotNull(objects);
      fail("should throw AssumptionViolatedException");
    }
    catch (AssumptionViolatedException e) {
      // expected
    }
  }

  @Test
  public void assumeNotNullThrowsExceptionForNullArray() {

    try {
      assumeNotNull((Object[])null);
      fail("should throw NullPointerException");
    }
    catch (NullPointerException e) {
      // expected
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void assumeNotNullPasses() {

    Object[] objects = {1, 2};
    assumeNotNull(objects);
  }

  @Test
  public void assumeNoExceptionThrows() {

    final Throwable exception = new NullPointerException();

    try{
      assumeNoException(exception);
      fail("Should have thrown exception");
    }
    catch (AssumptionViolatedException e) {
      assertThat(e.getCause(), is(exception));
    }
  }

}

package com.company.app;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.runner.JUnitCore.runClasses;

/*
The ExpectedException Rule allows in-test specification of expected exception types and messages
 */
public class H_ExpectedExceptionTest {

  public static class HasExpectedException {

    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsNothing() {

    }

    @Test
    public void throwsNullPointerException() {
      thrown.expect(NullPointerException.class);
      throw new NullPointerException();
    }

    @Test
    public void throwsNullPointerExceptionWithMessage() {
      thrown.expect(NullPointerException.class);
      thrown.expectMessage("happened?");
      thrown.expectMessage(startsWith("What"));
      throw new NullPointerException("What happened?");
    }

  }

  @Test
  public void testExpectedException() {
    runClasses(HasExpectedException.class);
  }

}

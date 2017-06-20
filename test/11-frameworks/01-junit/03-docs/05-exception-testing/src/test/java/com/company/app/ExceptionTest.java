package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class ExceptionTest {

  // Expected Exceptions
  @Test(expected = IndexOutOfBoundsException.class)
  public void empty() {
    new ArrayList<Object>().get(0);
  }

  // Deeper Testing of the Exception
  @Test
  public void testExceptionMessage() {
    try{
      new ArrayList<Object>().get(0);
      fail("Expected an IndexOutOfBoundsException to be thrown");
    }
    catch(IndexOutOfBoundsException indexOutOfBoundsException) {
      assertThat(indexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
    }
  }

  /*
  ExpectedException Rule
  This rule lets you indicate not only what exception you are expecting,
  but also the exception message you are expecting.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void shouldTestExceptionMessage() throws IndexOutOfBoundsException {
    List<Object> list = new ArrayList<Object>();

    thrown.expect(IndexOutOfBoundsException.class);
    thrown.expectMessage("Index: 0, Size: 0");
    list.get(0); // execution will never get pass this line
  }
}

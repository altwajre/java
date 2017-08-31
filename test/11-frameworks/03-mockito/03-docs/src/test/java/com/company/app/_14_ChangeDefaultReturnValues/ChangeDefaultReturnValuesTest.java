package com.company.app._14_ChangeDefaultReturnValues;

import org.junit.Test;
import org.mockito.exceptions.verification.SmartNullPointerException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.mock;

/*
14. Changing default return values of unstubbed invocations
You can create a mock with specified strategy for its return values.
 */
public class ChangeDefaultReturnValuesTest {
  interface Bar {
    void boo();
  }

  class Foo {
    Foo getSomeClass() {
      return null;
    }

    Bar getSomeInterface() {
      return null;
    }

    Bar getBarWithParams(int x, String y) {
      return null;
    }

    void boo() {
    }
  }

  @Test
  public void shouldThrowSmartNPEWhenMethodReturnsClass() {
    Foo mock = mock(Foo.class, RETURNS_SMART_NULLS);
    Foo foo = mock.getSomeClass();

    try{
      foo.boo();
//      fail();
    }
    catch (SmartNullPointerException e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void shouldThrowSmartNPEWhenMethodReturnsInterface() {
    Foo mock = mock(Foo.class, RETURNS_SMART_NULLS);
    Bar bar = mock.getSomeInterface();
    try{
      bar.boo();
      System.out.println("invoking fail()");
      fail();
    }
    catch (SmartNullPointerException e) {
      System.out.println(e.getMessage());
    }
  }
}

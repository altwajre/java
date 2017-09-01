package com.company.app._13_Spy;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
13. Spying on real objects
You can create spies of real objects. When you use the spy then the real methods are called (unless a method was stubbed).

Real spies should be used carefully and occasionally, for example when dealing with legacy code.
 */
public class SpyTest {
  @Test
  public void testSpy() {
    List list = new LinkedList<>();
    List spy = spy(list);

    // optionally, you can stub out some methods
    when(spy.size()).thenReturn(100);

    // using the spy calls *real* methods
    spy.add("one");
    spy.add("two");

    // prints "one" - the first element of a list
    System.out.println(spy.get(0));

    // size() method was stubbed - 100 is printed
    System.out.println(spy.size());

    // optionally, you can verify
    verify(spy).add("one");
    verify(spy).add("two");
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void useWhenForStubbingSpy() {
    List list = new LinkedList();
    List spy = spy(list);

    // Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
    when(spy.get(0)).thenReturn("foo");
  }

  @Test
  public void useDoMethodForStubbingSpy() {
    List<String> list = new LinkedList<>();
    List<String> spy = spy(list);

    // Arrange
    // you have to use doReturn() for stubbing spy
    doReturn("foo").when(spy).get(0);

    // Act
    final String foo = spy.get(0);
    System.out.println(foo);

    // Assert
    assertThat(foo, equalTo("foo"));

    // verify invocations
    verify(spy, times(1)).get(0);
  }
}

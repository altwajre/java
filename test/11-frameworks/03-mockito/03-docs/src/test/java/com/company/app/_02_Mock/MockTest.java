package com.company.app._02_Mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockTest {
  /*
  2. Stubbing
  - By default, for all methods that return a value, a mock will return either null, a primitive/primitive wrapper value,
    or an empty collection as appropriate. For example 0 for an int/Integer and false for a boolean/Boolean.
  - Stubbing can be overridden: for example common stubbing can go to fixture setup but the test methods can override it.
    Please note that overriding stubbing is a potential code smell that points out too much stubbing
  - Once stubbed, the method will always return a stubbed value, regardless of how many times it is called
  - Last stubbing is more important - when you stubbed the same arguments many times.
   */

  @Test
  public void stubbing() {
    // we can mock concrete classes, not just interfaces
    LinkedList mockedList = mock(LinkedList.class);

    // stubbing
    when(mockedList.get(0)).thenReturn("first");
    when(mockedList.get(1)).thenReturn(new RuntimeException());

    // following prints "first"
    System.out.println(mockedList.get(0));
    // following throws runtime exception
    System.out.println(mockedList.get(1));
    // following prints "null" because get(999) was not stubbed
    System.out.println(mockedList.get(999));

    //Although it is possible to verify a stubbed invocation, usually it's just redundant
    //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
    //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced?
    verify(mockedList, times(1)).get(0);
  }

  // we can mock concrete classes, not just interfaces
  @Mock
  private LinkedList mockList;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void stubbingBDD() {
    // given
    given(mockList.get(0))
        .willReturn("first");
    given(mockList.get(1))
        .willReturn(new RuntimeException());

    // when
    final String first = (String) mockList.get(0);
    final String exception = mockList.get(1).toString();

    // then
    then(first).isEqualTo("first");
    System.out.println(exception);
    then(exception).contains("RuntimeException");

    // verify invocations
    verify(mockList, times(1)).get(0);
  }

  @Test
  public void stubbingTDD() {
    // Arrange
    when(mockList.get(0))
        .thenReturn("first");
    when(mockList.get(1))
        .thenReturn(new RuntimeException());

    // Act
    final String first = (String) mockList.get(0);
    final String exception = mockList.get(1).toString();

    // Assert
    assertThat(first, equalTo("first"));
    System.out.println(exception);
    assertThat(exception, containsString("RuntimeException"));

    // verify invocations
    verify(mockList, times(1)).get(0);
  }
}

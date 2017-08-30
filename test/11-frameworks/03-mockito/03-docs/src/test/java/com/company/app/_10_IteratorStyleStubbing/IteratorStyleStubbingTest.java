package com.company.app._10_IteratorStyleStubbing;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.BDDAssertions.then;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class MyClass {
  public String someMethod(String input){
    return "Default";
  }
}
public class IteratorStyleStubbingTest {
  @Mock
  private MyClass mock;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testIteractorStyleStubbingBDD() {
    // given
    given(mock.someMethod("hello"))
        .willReturn("First")
        .willReturn("Second");

    // when
    // First call: prints "First"
    final String firstCall = mock.someMethod("hello");
    System.out.println(firstCall);

    // then
    then(firstCall).isEqualTo("First");

    // when
    // Second call: prints "Second"
    final String secondCall = mock.someMethod("hello");
    System.out.println(secondCall);

    // then
    then(secondCall).isEqualTo("Second");

    // Any consecutive call: prints "Second" as well (last stubbing wins)
    System.out.println(mock.someMethod("hello"));
  }

  @Test
  public void testIteractorStyleStubbing() {
    // Arrange
    when(mock.someMethod("hello"))
        .thenReturn("First")
        .thenReturn("Second");

    // Act
    // First call: prints "First"
    final String firstCall = mock.someMethod("hello");
    System.out.println(firstCall);

    // Assert
    assertThat(firstCall, equalTo("First"));

    // Act
    // Second call: prints "Second"
    final String secondCall = mock.someMethod("hello");
    System.out.println(secondCall);

    // Assert
    assertThat(secondCall, equalTo("Second"));

    // Any consecutive call: prints "Second" as well (last stubbing wins)
    System.out.println(mock.someMethod("hello"));
  }
  /*
  First
  Second
  Second
   */

  // Expected Exceptions
  @Test(expected = RuntimeException.class)
  public void testIteractorStyleStubbingException() {
    when(mock.someMethod("some arg"))
        .thenReturn("foo")
        .thenThrow(new RuntimeException());

    // First call: prints "foo"
    final String firstCall = mock.someMethod("some arg");
    System.out.println(firstCall);
    assertThat(firstCall, containsString("foo"));

    // Second call: throws runtime exception
    System.out.println(mock.someMethod("some arg"));
  }

}

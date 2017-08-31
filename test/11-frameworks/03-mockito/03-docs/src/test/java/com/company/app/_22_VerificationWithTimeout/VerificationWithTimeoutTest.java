package com.company.app._22_VerificationWithTimeout;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/*
22. Verification with timeout

Allows verifying with timeout. It causes a verify to wait for a specified period of time for a desired interaction
rather than fails immediately if had not already happened.
 */
public class VerificationWithTimeoutTest {
  class MyClass {
    public String someMethod(String input) {
      return "Default";
    }
  }

  @Mock
  private MyClass mock;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testVerificationWithTimeout() {
    // given
    given(mock.someMethod("hello"))
        .willReturn("one");

    // when
    final String actual = mock.someMethod("hello");

    // then
    // verify invocations with timeout
    verify(mock, timeout(100)).someMethod("hello");
    verify(mock, timeout(100).times(1)).someMethod("hello");
    then(actual).isEqualTo("one");
  }

  @Test
  public void testIteractorStyleStubbing() {
    // given
    given(mock.someMethod("hello"))
        .willReturn("one", "two", "three");

    // when
    final String one = mock.someMethod("hello");
    final String two = mock.someMethod("hello");
    final String three = mock.someMethod("hello");

    // then
    verify(mock, timeout(100).times(3)).someMethod("hello");
    verify(mock, timeout(100).atLeast(2)).someMethod("hello");
    then(one).isEqualTo("one");
    then(two).isEqualTo("two");
    then(three).isEqualTo("three");
  }
}

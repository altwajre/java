package com.company.app._11_StubbingWithCallbacks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/*
11. Stubbing with callbacks
Allows stubbing with generic Answer interface.
 */
public class StubbingWithCallbacksTest {
  interface IMethods {
    String simpleMethod(String argument);
  }

  @Mock
  private IMethods mock;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testStubbingWithCallbacks() {
    when(mock.simpleMethod(anyString()))
        .thenAnswer(new Answer<String>() {
          @Override
          public String answer(InvocationOnMock invocation) throws Throwable {
            String arg = invocation.getArgument(0);
            return invocation.getMethod().getName() + "-" + arg;
          }
        });

    assertThat(mock.simpleMethod("test"), equalTo("simpleMethod-test"));
  }
}

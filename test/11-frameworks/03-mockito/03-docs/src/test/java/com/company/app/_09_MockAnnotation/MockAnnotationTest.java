package com.company.app._09_MockAnnotation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/*
9. Shorthand for mocks creation - #Mock annotation
- Minimizes repetitive mock creation code
- Makes the test class more readable
- Makes the verification error easier to read because the field name is used to identify the mock
 */
public class MockAnnotationTest {
  @Mock
  private LinkedList mockedList;

  @Before
  public void setup() {
    // Important: This needs to be somewhere in the base class or a test runner
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void stubbing() {
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
    verify(mockedList).get(0);
  }
}

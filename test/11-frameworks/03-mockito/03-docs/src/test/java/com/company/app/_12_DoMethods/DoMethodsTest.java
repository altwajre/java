package com.company.app._12_DoMethods;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.doThrow;

// 12. doReturn()|doThrow()| doAnswer()|doNothing()|doCallRealMethod() family of methods
public class DoMethodsTest {
  @Mock
  private LinkedList mockedList;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = RuntimeException.class)
  public void doThrowOnVoidMethod() {
    /*
    Stubbing void methods requires a different approach from when(Object)
    because the compiler does not like void methods inside brackets
     */
//    when(mockedList.clear()).thenThrow(new RuntimeException()); // COMPILE ERROR

    doThrow(new RuntimeException()).when(mockedList).clear();

    mockedList.clear();
  }
}

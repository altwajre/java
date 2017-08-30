package com.company.app._05_StubbingExceptions;

import org.junit.Test;

import java.util.LinkedList;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

// 5. Stubbing void methods with exceptions
public class StubbingExceptionsTest {
  @Test(expected = RuntimeException.class)
  public void stub_void_methods_with_exceptions() {
    LinkedList mockedList = mock(LinkedList.class);
    doThrow(new RuntimeException()).when(mockedList).clear();
    // following throws RuntimeException:
    mockedList.clear();
  }
}

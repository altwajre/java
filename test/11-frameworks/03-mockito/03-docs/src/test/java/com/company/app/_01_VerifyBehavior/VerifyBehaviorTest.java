package com.company.app._01_VerifyBehavior;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
1. Let's verify some behaviour
Once created, a mock will remember all interactions. Then you can selectively verify whatever interactions you are
interested in.
*/
public class VerifyBehaviorTest {
  @Test
  public void verifyBehavior() {
    // mock creation
    List mockedList = mock(List.class);

    // using mock object
    mockedList.add("one");
    mockedList.clear();

    // verify invocations
    verify(mockedList).add("one");
    verify(mockedList).clear();
  }
}

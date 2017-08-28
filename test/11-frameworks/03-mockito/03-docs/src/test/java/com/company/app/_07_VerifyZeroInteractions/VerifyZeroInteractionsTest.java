package com.company.app._07_VerifyZeroInteractions;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class VerifyZeroInteractionsTest {
  // 7. Making sure interaction(s) never happened on mock
  @Test
  public void verify_interaction_never_happen_on_mock() {
    List mockOne = mock(List.class);
    List mockTwo = mock(List.class);
    List mockThree = mock(List.class);

    // using mocks - only mockOne is interacted
    mockOne.add("one");

    // ordinary verification
    verify(mockOne).add("one");

    // verify that method was never called on a mock
    verify(mockOne, never()).add("two");

    // verify that other mocks were not interacted
    verifyZeroInteractions(mockTwo, mockThree);
  }
}

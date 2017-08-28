package com.company.app._08_VerifyNoMoreInteractions;

import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class VerifyNoMoreInteractionsTest {
  // 8. Finding redundant invocations
  @Test
  public void find_redundant_invocations() {
    List mockedList = mock(List.class);

    // using mock
    mockedList.add("one");
    mockedList.add("two"); // comment out this line will pass

    verify(mockedList).add("one");

    // following verification will fail
    verifyNoMoreInteractions(mockedList);
  }
/*
org.mockito.exceptions.verification.NoInteractionsWanted:
No interactions wanted here:
-> at com.company.app.MockitoTest.find_redundant_invocations(MockitoTest.java:226)
But found this interaction:
-> at com.company.app.MockitoTest.find_redundant_invocations(MockitoTest.java:221)
***
For your reference, here is the list of all invocations ([?] - means unverified).
1. -> at com.company.app.MockitoTest.find_redundant_invocations(MockitoTest.java:220)
2. [?]-> at com.company.app.MockitoTest.find_redundant_invocations(MockitoTest.java:221)
 */
}

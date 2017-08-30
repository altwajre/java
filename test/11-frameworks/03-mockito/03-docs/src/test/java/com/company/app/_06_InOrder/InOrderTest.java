package com.company.app._06_InOrder;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

/*
6. Verification in order
A + B can be mixed together at will
Verification in order is flexible - you don't have to verify all interactions one-by-one but only those that you
are interested in testing in order. Also you can create an InOrder object passing only the mocks that are relevant
for in-order verification.
 */
public class InOrderTest {
  @Test
  public void verification_in_order() {
    // A. Single mock whose methods must be invoked in a particular order
    List singleMock = mock(List.class);

    // using a single mock
    singleMock.add("was added first");
    singleMock.add("was added second");

    // create an inOrder verifier for a single mock
    InOrder inOrder = inOrder(singleMock);

    // following will make sure that add is first called with "was added first", then with "was added second"
    inOrder.verify(singleMock).add("was added first");
    inOrder.verify(singleMock).add("was added second");

    // B. Multiple mocks that must be used in a particular order
    List firstMock = mock(List.class);
    List secondMock = mock(List.class);

    // using mocks
    firstMock.add("was called first");
    secondMock.add("was called second");

    // create inOrder object passing any mocks that need to be verified in order
    InOrder inOrder2 = inOrder(firstMock, secondMock);

    // following will make sure that firstMock was called before secondMock
    inOrder2.verify(firstMock).add("was called first");
    inOrder2.verify(secondMock).add("was called second");
  }
}

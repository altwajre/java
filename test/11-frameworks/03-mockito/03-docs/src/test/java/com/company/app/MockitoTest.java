package com.company.app;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/*
https://static.javadoc.io/org.mockito/mockito-core/2.8.47/org/mockito/Mockito.html
 */
public class MockitoTest {
  /*
  1. Let's verify some behaviour
  Once created, a mock will remember all interactions. Then you can selectively verify whatever interactions you are
  interested in.
   */
  @Test
  public void verify_behavior() {
    // mock creation
    List mockedList = mock(List.class);

    // using mock object
    mockedList.add("one");
    mockedList.clear();

    // verification
    verify(mockedList).add("one");
    verify(mockedList).clear();
  }

  /*
  2. Stubbing
  - By default, for all methods that return a value, a mock will return either null, a primitive/primitive wrapper value,
    or an empty collection as appropriate. For example 0 for an int/Integer and false for a boolean/Boolean.
  - Stubbing can be overridden: for example common stubbing can go to fixture setup but the test methods can override it.
    Please note that overriding stubbing is a potential code smell that points out too much stubbing
  - Once stubbed, the method will always return a stubbed value, regardless of how many times it is called
  - Last stubbing is more important - when you stubbed the same arguments many times.
   */
  @Test
  public void stubbing() {
    // we can mock concrete classes, not just interfaces
    LinkedList mockedList = mock(LinkedList.class);

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

  /*
  3. Argument matchers
  - Argument matchers allow flexible verification or stubbing.
  - If you are using argument matchers, all arguments have to be provided by matchers.
   */
  @Test
  public void argument_matchers() {
    LinkedList mockedList = mock(LinkedList.class);

    // stubbing using built-in anyInt() argument matcher
    when(mockedList.get(anyInt())).thenReturn("element");

    // following prints "element"
    System.out.println(mockedList.get(999));

    // you can also verify using an argument matcher
    verify(mockedList).get(anyInt());

    // stubbing using custom matcher (let's say isValid() returns your own matcher implementation)
    when(mockedList.contains(argThat(isEvenNumber()))).thenReturn(true);

    System.out.println(mockedList.contains(8)); // true because 8 is even number
    System.out.println(mockedList.contains(11)); // false because 11 is not even number

    // verify custom argument matcher
    verify(mockedList).contains(argThat(isEvenNumber()));
  }

  private ArgumentMatcher<Integer> isEvenNumber() {
    return new ArgumentMatcher<Integer>() {
      @Override
      public boolean matches(Object obj) {
        int input = (int) obj;
        if (input % 2 == 0) {
          return true;
        } else {
          return false;
        }
      }
    };
  }

  /*
  4. Verifying exact number of invocations / at least x /never
  - times(1) is the default. Therefore using times(1) explicitly can be omitted.
   */
  @Test
  public void verify_number_of_invocations() {
    LinkedList mockedList = mock(LinkedList.class);

    // using mock
    mockedList.add("once");

    mockedList.add("twice");
    mockedList.add("twice");

    mockedList.add("three times");
    mockedList.add("three times");
    mockedList.add("three times");

    // following two verifications work exactly the same - times(1) is used by default
    verify(mockedList).add("once");
    verify(mockedList, times(1)).add("once");

    // exact number of invocations verification
    verify(mockedList, times(2)).add("twice");
    verify(mockedList, times(3)).add("three times");

    // verification using never(). never() is an alias to times(0)
    verify(mockedList, never()).add("never happened");

    // verification using atLeast()/atMost()
    verify(mockedList, atLeastOnce()).add("once");

    verify(mockedList, atLeast(2)).add("three times");
    verify(mockedList, atMost(5)).add("three times");
  }

  // 5. Stubbing void methods with exceptions
  @Test(expected = RuntimeException.class)
  public void stub_void_methods_with_exceptions() {
    LinkedList mockedList = mock(LinkedList.class);
    doThrow(new RuntimeException()).when(mockedList).clear();
    // following throws RuntimeException:
    mockedList.clear();
  }

  /*
  6. Verification in order
  A + B can be mixed together at will
  Verification in order is flexible - you don't have to verify all interactions one-by-one but only those that you
  are interested in testing in order. Also you can create an InOrder object passing only the mocks that are relevant
  for in-order verification.
   */
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

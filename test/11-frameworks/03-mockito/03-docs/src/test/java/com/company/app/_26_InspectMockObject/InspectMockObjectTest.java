package com.company.app._26_InspectMockObject;

import org.junit.Test;
import org.mockito.MockingDetails;
import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/*
26. Mocking details
Mockito offers API to inspect the details of a mock object.
This API is useful for advanced users and mocking framework integrations.
 */
public class InspectMockObjectTest {
  @Test
  public void testInspectMockObject() {
    LinkedList mock = mock(LinkedList.class);
    List list = new LinkedList<>();
    List spy = spy(list);

    // To identify whether a particular object is a mock or a spy
    System.out.println("mock object isMock=" + Mockito.mockingDetails(mock).isMock());
    System.out.println("spy object isSpy=" + Mockito.mockingDetails(spy).isSpy());

    // Getting details like type to mock or default answer
    MockingDetails details = mockingDetails(mock);
    System.out.println("TypeToMock: " + details.getMockCreationSettings().getTypeToMock());
    System.out.println("DefaultAnswer: " + details.getMockCreationSettings().getDefaultAnswer());
  }
/*
mock object isMock=true
spy object isSpy=true
TypeToMock: class java.util.LinkedList
DefaultAnswer: RETURNS_DEFAULTS
 */

  @Test
  public void testInspectMockObjectInteractionsAndStubbing() {
    // we can mock concrete classes, not just interfaces
    LinkedList mock = mock(LinkedList.class);

    // stubbing
    when(mock.get(0)).thenReturn("first");
    when(mock.get(1)).thenReturn(new RuntimeException());

    // following prints "first"
    mock.get(0);
    // following throws runtime exception
    mock.get(1);
    // following prints "null" because get(999) was not stubbed
    mock.get(999);

    // Getting interactions and stubbing of the mock
    MockingDetails details = mockingDetails(mock);
    System.out.println("Invocations: " + details.getInvocations());
    System.out.println("Stubbings: " + details.getStubbings());

    // Printing all interactions (including stubbing, unused stubs)
    System.out.println("printInvocations: " + mockingDetails(mock).printInvocations());
  }
/*
Invocations: [linkedList.get(0);, linkedList.get(1);, linkedList.get(999);]
Stubbings: [linkedList.get(0); stubbed with: [Returns: first], linkedList.get(1); stubbed with: [Returns: java.lang.RuntimeException]]
printInvocations: [Mockito] Interactions of: Mock for LinkedList, hashCode: 225344427
 1. linkedList.get(0);
  -> at com.company.app._26_InspectMockObject.InspectMockObjectTest.testInspectMockObjectInteractionsAndStubbing(InspectMockObjectTest.java:42)
   - stubbed -> at com.company.app._26_InspectMockObject.InspectMockObjectTest.testInspectMockObjectInteractionsAndStubbing(InspectMockObjectTest.java:38)
 2. linkedList.get(1);
  -> at com.company.app._26_InspectMockObject.InspectMockObjectTest.testInspectMockObjectInteractionsAndStubbing(InspectMockObjectTest.java:44)
   - stubbed -> at com.company.app._26_InspectMockObject.InspectMockObjectTest.testInspectMockObjectInteractionsAndStubbing(InspectMockObjectTest.java:39)
 3. linkedList.get(999);
  -> at com.company.app._26_InspectMockObject.InspectMockObjectTest.testInspectMockObjectInteractionsAndStubbing(InspectMockObjectTest.java:46)
 */
}

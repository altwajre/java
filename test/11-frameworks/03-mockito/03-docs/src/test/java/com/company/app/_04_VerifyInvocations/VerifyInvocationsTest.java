package com.company.app._04_VerifyInvocations;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/*
4. Verifying exact number of invocations / at least x /never
- times(1) is the default. Therefore using times(1) explicitly can be omitted.
 */
public class VerifyInvocationsTest {
  interface CustomerListener {
    void found(Customer customer);
  }
  @Data
  @AllArgsConstructor
  class Customer {
    private int id;
    private String name;
    private int age;
  }
  class DataStore {
    private Map<Integer, Customer> customers = new HashMap<>();
    public void add(Customer customer) {
      customers.put(customer.getId(), customer);
    }
    public void process(CustomerListener listener, int id) {
      listener.found(customers.get(id));
    }
  }

  @Test
  public void verifyNumberOfInvocations() {
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

  @Test
  public void processNotifiesListenerOnId() {
    CustomerListener listener = mock(CustomerListener.class);

    DataStore dataStore = new DataStore();
    final int id = 1;
    final Customer tom = new Customer(id, "Tom", 18);
    dataStore.add(tom);

    dataStore.process(listener, id);

    // Verify CustomerListener.found() is invoked once
    verify(listener, times(1)).found(tom);
  }
}

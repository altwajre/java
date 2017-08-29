package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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

public class ListenerTest {
  @Test
  public void testListener() {
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

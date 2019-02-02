package domain.model;

import java.util.HashMap;
import java.util.Map;

public enum Store {
  INSTANCE;

  private Map<Integer, Customer> customers = new HashMap<>();

  Store() {
    Customer customer1 = new Customer(1, "Tom", 68);
    customers.put(customer1.getId(), customer1);
    Customer customer2 = new Customer(2, "Harry", 28);
    customers.put(customer2.getId(), customer2);
  }

  public Customer getCustomer(Integer id) {
    return customers.get(id);
  }
}

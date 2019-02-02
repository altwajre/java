package domain.model;

import org.junit.Test;

public class StoreTest {
  @Test
  public void retriveCustomer() {
    Store store = Store.INSTANCE;
    Customer customer = store.getCustomer(1);
    System.out.println(customer);
  }
}
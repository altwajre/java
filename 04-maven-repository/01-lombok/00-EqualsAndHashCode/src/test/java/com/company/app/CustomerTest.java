package com.company.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

  @Test
  public void testCustomersEqual() {
    Customer customer1 = new Customer("Tom", 18);
    Customer customer2 = new Customer("Tom2", 28);
    assertEquals("Two Customer should be equal", customer1, customer2);
  }

  @Test
  public void sortTest() {
    Customer c = new Customer("c", 3);
    Customer a = new Customer("a", 1);
    Customer b = new Customer("b", 2);
    List<Customer> customers = new ArrayList<>();
    customers.add(b);
    customers.add(c);
    customers.add(a);
    System.out.println(customers);


  }
}
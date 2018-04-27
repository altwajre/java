package com.company.app;

import com.company.app.model.Customer;

public class App {
  public static void main(String[] args) {
    Customer customer = new CustomerFactory().create();
    System.out.println(customer);
  }
}

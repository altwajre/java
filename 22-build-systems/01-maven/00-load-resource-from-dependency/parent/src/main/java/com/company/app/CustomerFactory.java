package com.company.app;

import com.company.app.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class CustomerFactory {
  public Customer create(){
    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("data/customer.json");

    Customer customer = null;
    try {
      customer = mapper.readValue(inputStream, Customer.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return customer;
  }
}

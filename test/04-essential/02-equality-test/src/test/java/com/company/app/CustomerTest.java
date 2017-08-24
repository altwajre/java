package com.company.app;

import org.junit.Test;

public class CustomerTest {
  @Test
  public void testEquality() throws InstantiationException, IllegalAccessException {
    EqualityValidator.validate(Customer.class);
  }
}
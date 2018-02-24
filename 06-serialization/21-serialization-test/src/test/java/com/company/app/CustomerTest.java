package com.company.app;

import org.junit.Test;

import java.io.IOException;

public class CustomerTest {
  @Test
  public void testSerialization() throws IOException {
    final Customer tom = new Customer("Tom", 18);
    SerializationValidator.validate(tom);
  }

  @Test
  public void testCloneSerialization() {
    final Customer dick = new Customer("Dick", 28);
    SerializationValidator.validateClone(dick, Customer.class);
  }
}
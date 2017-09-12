package com.company.app;

import org.junit.Test;

import java.io.IOException;

public class CustomerTest {
  @Test
  public void testSerialization() throws IOException {
    final Customer tom = Customer.builder()
        .name("Tom")
        .age(18)
        .build();
    SerializationValidator.validate(tom);
  }

  @Test
  public void testCloneSerialization() {
    final Customer dick = Customer.builder()
        .name("Dick")
        .age(28)
        .build();
    SerializationValidator.validateClone(dick, Customer.class);
  }
}
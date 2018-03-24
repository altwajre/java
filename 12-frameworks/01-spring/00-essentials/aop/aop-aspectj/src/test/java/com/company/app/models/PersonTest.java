package com.company.app.models;

import org.junit.Test;

public class PersonTest {

  @Test
  public void getName() {
    Person tom = new Person();
    tom.setName("Tom");
    System.out.println(tom.getName());
  }
}
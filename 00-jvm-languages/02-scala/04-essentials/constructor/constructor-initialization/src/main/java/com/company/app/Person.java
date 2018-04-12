package com.company.app;

public class Person {
  public Person (String firstName, String lastName) {
    this.name = firstName + " " + lastName;
  }
  private String name;
  public String getName() {
    return name;
  }
}

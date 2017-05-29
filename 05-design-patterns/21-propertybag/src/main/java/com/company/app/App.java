package com.company.app;

class Employee {
  private String name;

  public Employee() {
    this.name = "Tom";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

public class App {

  public static void main(String[] args) {

    ofValueTest();

    ofDescriptionTest();

  }

  private static void ofValueTest() {
    PropertyBAG string = PropertyBAG.ofValue("foo");
    System.out.println(string.getValue());

    PropertyBAG integer = PropertyBAG.ofValue(18);
    System.out.println(integer.getValue());

    PropertyBAG employee = PropertyBAG.ofValue(new Employee());
    System.out.println(((Employee)employee.getValue()).getName());
  }

  private static void ofDescriptionTest() {
    PropertyBAG string = PropertyBAG.ofDescription(String.class, "Hello");
    System.out.println(string.getDescriptionDefaultValue());

    PropertyBAG integer = PropertyBAG.ofDescription(Integer.class, 18);
    System.out.println(integer.getDescriptionDefaultValue());

    PropertyBAG employee = PropertyBAG.ofDescription(Employee.class, new Employee());
    System.out.println(employee.getDescriptionDefaultValue().getClass().getSimpleName());
    System.out.println(((Employee) employee.getDescriptionDefaultValue()).getName());
  }
}

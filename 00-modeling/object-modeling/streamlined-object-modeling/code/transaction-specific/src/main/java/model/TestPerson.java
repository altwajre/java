package model;

import util.BusinessRuleException;

public class TestPerson {
  public static void main(String[] args) {
    Person person = null;

    /*
    Try to create Person test object.
    If fail, print business rule exception message.
    If pass print person test object.
    Tests of accessors and methods can be added to this script.
     */
    try {
      person = Person.testPerson();
    } catch (BusinessRuleException e) {
      System.out.println("BOOM: " + e.getMessage());
    }

    System.out.println("\n" + person);
  }
}


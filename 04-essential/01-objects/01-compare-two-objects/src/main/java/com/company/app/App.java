package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class User {
  private String name;
  private int age;
}

/*

1, == operator compare two objects are the same instance
2, .equals() method compare two objects are equivalent
3, compare strings should use .equals() because we are comparing the string values
 */
public class App {
  public static void main(String[] args) {
    equalOperator();

    equalMethod();

    compareStrings();
  }

  // == operator compare two objects are the same instance
  private static void equalOperator() {
    System.out.println("#equalOperator");
    User user1 = new User("Tom", 18);
    User user2 = user1;

    if(user1 == user2) {
      System.out.println("PASS: user1 and user2 reference the same object");
    }
    else {
      System.err.println("FAIL: user1 and user2 DO NOT reference the same object");
    }
  }
/*
PASS: user1 and user2 reference the same object
 */

  // .equal() method compare two objects are equivalent
  private static void equalMethod() {
    System.out.println("#equalMethod");
    User user1 = new User("Tom", 18);
    User user2 = new User("Tom", 18);
    if(user1.equals(user2)) {
      System.out.println("PASS: user1 and user2 have the same field values (equivalent)");
    }
    else {
      System.err.println("FAIL: user1 and user2 DO NOT have the same field values (NOT equivalent)");
    }
  }
/*
PASS: user1 and user2 have the same field values (equivalent)
 */

  // compare strings should use .equals() because we are comparing the string values
  private static void compareStrings() {
    System.out.println("#compareStrings");
    String name1 = "Tom";
    String name2 = "Tom";

    if(name1.equals(name2)) {
      System.out.println("PASS: name1 and name2 have the same values (equivalent)");
    }
    else {
      System.out.println("FAIL: name1 and name2 DO NOT have the same values (equivalent)");
    }
  }
/*
PASS: name1 and name2 have the same values (equivalent)
 */
}

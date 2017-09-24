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
2, .equal() method compare two objects are equivalent

 */
public class App {
  public static void main(String[] args) {
    equalOperator();

//    equalMethod();
  }

  // .equal() method compare two objects are equivalent
  private static void equalMethod() {
    User user1 = new User("Tom", 18);
    User user2 = new User("Tom", 18);
    if(user1.equals(user2)) {
      System.out.println("PASS: user1 and user2 have the same field values (equivalent)");
    }
    else {
      System.err.println("PASS: user1 and user2 DO NOT have the same field values (NOT equivalent)");
    }
  }
/*
PASS: user1 and user2 have the same field values (equivalent)
 */

  // == operator compare two objects are the same instance
  private static void equalOperator() {
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
}

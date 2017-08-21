package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
class Customer {
  private String name;
  private int age;
}

@Data
@AllArgsConstructor
class User {
  private String name;
  private int age;
}

// Checkout the generated the classes under target/classes
public class App {
  public static void main(String[] args) {
    // Builder: @Builder includes @AllArgsConstructor
    System.out.println("# Builder");
    final Customer tom = Customer.builder().name("Tom").age(18).build();
    System.out.println(tom);
    final Customer harry = new Customer("Harry", 38);
    System.out.println(harry);

    // Constructor
    System.out.println("#Constructor");
    final User dick = new User("Dick", 28);
    System.out.println(dick);
  }
}
/*
# Builder
Customer(name=Tom, age=18)
Customer(name=Harry, age=38)
#Constructor
User(name=Dick, age=28)
 */

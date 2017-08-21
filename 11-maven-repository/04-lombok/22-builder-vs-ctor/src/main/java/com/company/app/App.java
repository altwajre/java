package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@NoArgsConstructor // @NoArgsConstructor is not allowed when @Builder is already used
class Customer {
  private String name;
  private int age;
}

@Data
@NoArgsConstructor
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
    final User ben = new User();
    ben.setName("Ben");
    ben.setAge(48);
    System.out.println(ben);

  }
}
/*
# Builder
Customer(name=Tom, age=18)
Customer(name=Harry, age=38)
#Constructor
User(name=Dick, age=28)
User(name=Ben, age=48)
 */

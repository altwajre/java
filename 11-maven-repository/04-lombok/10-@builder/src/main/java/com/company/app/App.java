package com.company.app;

import lombok.*;

import java.util.Set;

/*
https://projectlombok.org/features/Builder.html

The @Builder annotation produces complex builder APIs for your class.
@Builder lets you automatically produce the code required to have your class be instantiable with code such as:
Person.builder().name(“Tom”).age(8).build();

The @Singular annotation, lombok will treat that builder node as a collection, and it generates 2 'adder' methods
instead of a 'setter' method.
 */
@Value
@Builder
class BuilderExample {
  private String name;
  private int age;
  @Singular
  private Set<String> occupations;
}
@Data
class Customer {
  private String name;
  private int age;
  public Customer() {
    System.out.println("default constructor");
  }
  @Builder
  public Customer(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
public class App {
  public static void main(String[] args) {
    BuilderExample builderExample = BuilderExample.builder().name("Tom").age(10).build();
    System.out.println(builderExample);

    final BuilderExample builderExample1 = new BuilderExample("Dick", 28, null);
    System.out.println(builderExample1);

    // Customer.builder() calls @Builder ctor
    final Customer tom = Customer.builder().name("Tom").age(18).build();
    System.out.println(tom);

    // calls default ctor
    final Customer customer = new Customer();
    System.out.println(customer);
  }
}
/*
BuilderExample(name=Tom, age=10, occupations=[])
BuilderExample(name=Dick, age=28, occupations=null)
Customer(name=Tom, age=18)
default constructor
Customer(name=null, age=0)
 */

package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
class Customer {
  private String name;
  private int age;
}

/*
LinkedHashMap = Database
 */
public class App {
  public static void main(String[] args) {
    Map<Integer, Customer> customers = new LinkedHashMap<>();

    // POST
    customers.put(1, new Customer("Tom", 18));
    System.out.println("POST: map="+customers);

    // GET one item
    System.out.println("GET: customer="+ customers.get(1));

    // PUT
    Customer customer = customers.get(1);
    customer.setName("Dick");
    customer.setAge(28);
    System.out.println("PUT: customer="+ customers.get(1));

    // DELETE
    customers.remove(1);
    System.out.println("DELETE: map="+customers);

  }
}
/*
POST: map={1=Customer(name=Tom, age=18)}
GET: customer=Customer(name=Tom, age=18)
PUT: customer=Customer(name=Dick, age=28)
DELETE: map={}
 */

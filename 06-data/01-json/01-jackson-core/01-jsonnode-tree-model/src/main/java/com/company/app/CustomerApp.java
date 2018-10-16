package com.company.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerApp {
  private static ObjectMapper mapper = new ObjectMapper();
  public static void main(String[] args) throws IOException {
    jsonToPojo();

    jsonArrayToList();
  }

  private static void jsonArrayToList() throws IOException {
    JsonNode jsonNode = mapper.readTree(new File("src/main/resources/data/Customers.json"));
//    List<Customer> customers = JsonHelper.toPojo(jsonNode, List.class);
    List<Customer> customers = JsonHelper.toList(jsonNode.toString(), new TypeReference<List<Customer>>() { });
    System.out.println(customers);
    Customer customer = customers.get(0);
    System.out.println(customer);
  }
  /*
  [Customer(name=Tom, age=8), Customer(name=Harry, age=18)]
  Customer(name=Tom, age=8)
   */

  private static void jsonToPojo() throws IOException {
    JsonNode jsonNode = mapper.readTree(new File("src/main/resources/data/Customer.json"));
    Customer customer = JsonHelper.toPojo(jsonNode, Customer.class);
    System.out.println(customer);
  }
  /*
  Customer(name=Tom, age=8)
   */
}

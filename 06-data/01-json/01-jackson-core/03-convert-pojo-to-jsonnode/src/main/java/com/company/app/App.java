package com.company.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    Customer customer = new Customer();
    customer.setName("Tom");
    customer.setAge(28);

    System.out.println("# Pojo to JsonNode");
    JsonNode customerJson = mapper.convertValue(customer, JsonNode.class);
    System.out.println(customerJson);

    System.out.println("# JsonNode to Pojo");
    Customer jsonToPojo = mapper.treeToValue(customerJson, Customer.class);
    System.out.println(jsonToPojo);


  }
}
/*
output:
# Pojo to JsonNode
{"id":null,"name":"Tom","age":28}
# JsonNode to Pojo
Customer(id=null, name=Tom, age=28)
 */

package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class App {
  public static void main(String[] args) throws IOException {
    createJsonObjectFromString();
    createJsonObjectFromPOJO();
  }

  private static void createJsonObjectFromPOJO() throws IOException {
    System.out.println("\n#createJsonObjectFromPOJO");
    // load json from resources
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("json/customer.json");

    // convert inputStream to POJO
    System.out.println("Json to POJO:");
    Customer customer = new ObjectMapper().readValue(inputStream, Customer.class);
    System.out.println(customer + "\n");

    // create Vertx JsonObject from POJO
    JsonObject customerJson = JsonObject.mapFrom(customer);
    System.out.println("Vertx JsonObject: " + customerJson);
  }

  private static void createJsonObjectFromString() throws IOException {
    System.out.println("#createJsonObjectFromString");
    // load json from resources
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("json/customer.json");

    // convert inputStream to string
    System.out.println("Json to String:");
    String customerJsonString = toString(inputStream);
    System.out.println(customerJsonString + "\n");

    // create Vertx JsonObject from json string
    JsonObject customerJson = new JsonObject(customerJsonString);
    System.out.println("Vertx JsonObject: " + customerJson);
  }

  public static String toString(InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining("\n"));
    }
  }
}
/*
#createJsonObjectFromString
Json to String:
{
  "name": "Tom",
  "age": "18"
}

Vertx JsonObject: {"name":"Tom","age":"18"}

#createJsonObjectFromPOJO
Json to POJO:
Customer(id=0, name=Tom, age=18)

Vertx JsonObject: {"id":0,"name":"Tom","age":18}
 */

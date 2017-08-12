package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.Data;

import java.io.IOException;

@Data
class Address {
  private String street;
  private String city;
  private String state;
}

@Data
class Customer {
  private String name;
  private Integer age;
  private Address address;
}

@Data
class Car {
  private String model;
  private String color;
}

@Data
class Parking {
  private Customer customer;
  private Car car;
}

public class App {
  public static void main(String[] args) throws IOException {
    String json = Resources.toString(Resources.getResource("json/parking.json"), Charsets.UTF_8);
    System.out.println(json);
    Parking parking = new ObjectMapper().readValue(json, Parking.class);
    System.out.println("POJO: " + parking);
  }
}
/*
{
  "customer":
  {
    "name": "Tom",
    "age": "28",
    "address": {
      "street": "1 main st",
      "city": "Seattle",
      "state": "WA"
    }
  },
  "car":
  {
    "model": "BMW",
    "color": "Black"
  }
}

POJO: Parking(customer=Customer(name=Tom, age=28, address=Address(street=1 main st, city=Seattle, state=WA)), car=Car(model=BMW, color=Black))
 */

package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;

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
  public static void main(String[] args) throws Exception {

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("json/parking.json");

    Parking parking = new ObjectMapper().readValue(inputStream, Parking.class);
    System.out.println("POJO: "+parking);

  }

}
/*
POJO: Parking(customer=Customer(name=Tom, age=28, address=Address(street=1 main st, city=Seattle, state=WA)), car=Car(model=BMW, color=Black))
 */

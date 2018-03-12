package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.apache.commons.io.IOUtils;

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
    String text = IOUtils.toString(inputStream, "UTF-8");
    System.out.println(text);

    Parking parking = new ObjectMapper().readValue(classLoader.getResourceAsStream("json/parking.json"), Parking.class);
    System.out.println("POJO: "+parking);

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

package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

@Data
class Person {
  @JsonProperty
  private String name;
  @JsonProperty
  private int age;
}

@Data
class Student extends Person {
  @JsonProperty
  private String arpartment;
}

/*
http://www.tutorialspoint.com/jackson/jackson_objectmapper.htm
 */
public class App {
  public static void main(String[] args) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = "{\"name\":\"Mahesh\", \"age\":21, \"arpartment\":\"128\"}";

    Student student = deserialize(mapper, jsonString);

    serialize(mapper, student);
  }

  private static void serialize(ObjectMapper mapper, Student student) throws JsonProcessingException {
    System.out.println("#serialize json-string");
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    String jsonStr = mapper.writeValueAsString(student);
    System.out.println(jsonStr);
  }

  private static Student deserialize(ObjectMapper mapper, String jsonString) throws java.io.IOException {
    System.out.println("#deserialize");
    Student student = mapper.readValue(jsonString, Student.class);
    System.out.println(student);
    return student;
  }
}
/*
#deserialize
Student(arpartment=128)
#serialize json-string
{
  "name" : "Mahesh",
  "age" : 21,
  "arpartment" : "128"
}
 */
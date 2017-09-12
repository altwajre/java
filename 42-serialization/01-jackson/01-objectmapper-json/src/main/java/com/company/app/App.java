package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;

@Data
class Person {
    private String name;
    private int age;
}

@Data
class Student extends Person{
    private String arpartment;
}

/*
http://www.tutorialspoint.com/jackson/jackson_objectmapper.htm
 */
public class App
{
    public static void main( String[] args ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        Student student = mapper.readValue(jsonString, Student.class);
        System.out.println(student);

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonStr = mapper.writeValueAsString(student);
        System.out.println(jsonStr);
    }
}
/*
Student [ name:: Mahesh, age: 21]
{
  "name" : "Mahesh",
  "age" : 21
}
 */
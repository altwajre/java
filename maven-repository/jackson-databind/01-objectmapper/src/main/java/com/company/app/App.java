package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class Student{
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Student [ name:: " + name + ", age: " + age + "]";
    }
}

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
http://www.tutorialspoint.com/jackson/jackson_objectmapper.htm

output:
Student [ name:: Mahesh, age: 21]
{
  "name" : "Mahesh",
  "age" : 21
}
 */
package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

class User{
    private String name;
    private Integer age;

    // empty constructor is required for convert json to object
    public User(){}

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
public class App
{
    private static ObjectMapper MAPPER = new ObjectMapper();
    private static Integer id = 1;
    public static void main( String[] args )
    {
        User user = new User("Tom", 28);
        try {
            MAPPER.writeValue(new File("user_" + id++), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
user_1
 */

package com.company.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public static void main( String[] args )
    {

        String jsonUser = objToJson();

        jsonToObj(jsonUser);

    }

    private static String objToJson() {
        User user = new User("Tom", 28);

        String jsonUser = null;
        try {
            jsonUser = MAPPER.writeValueAsString(user);
            System.out.println(jsonUser);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonUser;
    }
    /*
    output:
    {"name":"Tom","age":28}
     */

    private static void jsonToObj(String jsonUser) {
        try {
            User jsonToObj = MAPPER.readValue(jsonUser, User.class);
            System.out.println(jsonToObj.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    output:
    Tom
     */

}

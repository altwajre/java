package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

class Address{
    private String street;
    private String city;
    private String zip;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
class User{
    private String name;
    private int age;
    private Address address;
    private String[] roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}

public class App
{
    public static void main( String[] args )
    {
        yamlToObject();
    }

    private static void yamlToObject() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            User user = mapper.readValue(new File("src/main/resources/yaml/user.yaml"), User.class);
            System.out.println(user.getName());
            System.out.println(user.getAddress().getStreet());
            System.out.println(user.getRoles()[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
Tom
1234 Pine st
Editor
 */

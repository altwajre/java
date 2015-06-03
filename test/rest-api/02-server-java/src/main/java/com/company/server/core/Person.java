package com.company.server.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private int age;
    private String name;

    public Person(){

    }

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @JsonProperty
    public int getAge()
    {
        return age;
    }

    @JsonProperty
    public String getName()
    {
        return name;
    }
}

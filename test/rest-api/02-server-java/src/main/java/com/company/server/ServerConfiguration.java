package com.company.server;

import io.dropwizard.Configuration;

public class ServerConfiguration extends Configuration {
    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    private int age;
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
}

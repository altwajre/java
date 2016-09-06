package com.company.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private int age;
    private String name;
    private List<String> messages;

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMessages(List<String> messages){
        this.messages = messages;
    }

    public List<String> getMessages(){
        return this.messages;
    }

    @Override
    public String toString(){
        return "User [age=" + age + ", name=" + name + ", " + "messages=" + messages + "]";
    }
}

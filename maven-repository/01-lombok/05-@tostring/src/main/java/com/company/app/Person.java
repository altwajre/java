package com.company.app;

import lombok.ToString;

@ToString(exclude = "id")
public class Person {
    final private int id;
    final private int age;
    final private String name;

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}

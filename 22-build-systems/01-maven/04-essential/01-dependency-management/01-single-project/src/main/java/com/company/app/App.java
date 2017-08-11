package com.company.app;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class Person {
    private String name;
    private int age;
}

public class App {
    public static void main(String[] args) {
        Person tom = Person
                .builder()
                .name("Tom")
                .age(10)
                .build();
        System.out.println(tom);
    }
}
/*
output:
Person(name=Tom, age=10)
 */
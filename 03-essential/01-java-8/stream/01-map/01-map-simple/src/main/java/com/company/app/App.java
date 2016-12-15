package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Map: select information from certain objects
map() takes a function as argument. the function is applied to each element, mapping it into a new element.
  */
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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

    @Override
    public String toString() {
        return "{name: " + name + ", age: " + age + "}";
    }
}

public class App {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(new Person("Tom", 8), new Person("Dick", 18));
        System.out.println(people);
        List<String> names = people.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());
        System.out.println(names);

        List<Integer> nameLengths = people.stream()
                .map(p -> p.getName())
                .map(n -> n.length())
                .collect(Collectors.toList());
        System.out.println(nameLengths);
    }
}

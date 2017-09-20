package com.company.app;

// https://en.wikipedia.org/wiki/Method_chaining
class Person {
    private String name;
    private int age;

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}

public class App {
    public static void main(String[] args) {
        new Person()
                .setName("Tom")
                .setAge(18)
                .introduce();
    }
}
/*
output:
Hello, my name is Tom and I am 18 years old.
 */

package com.company.app;

public class App {
    public static void main(String... args){
        Person person = new Person(1, 28, "Tom");
        System.out.println(person);
    }
}
/*
https://projectlombok.org/features/ToString.html
@ToString to let lombok generate an implementation of the toString() method.
By default, it’ll print your class name, along with each field, in order, separated by commas.

output:
Person(age=28, name=Tom)
 */
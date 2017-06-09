package com.company.app;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
class Person{
    private String name;
    private int age;
}
public class App 
{
    public static void main( String[] args )
    {
        Person tom = new Person();
        tom.name("Tom");
        tom.age(18);
        System.out.println(tom.name() + " is " + tom.age());
    }
}
/*
output:
Tom is 18
 */

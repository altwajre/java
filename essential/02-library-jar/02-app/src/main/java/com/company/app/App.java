package com.company.app;

import com.company.lib.Math;
import com.company.lib.Person;

public class App
{
    public static void main( String[] args )
    {
        Math math = new Math();
        System.out.printf("1 + 2 = %s\n", math.add(1, 2));
        Person person = new Person();
        person.setName("Tom");
        System.out.printf("Person's name is %s\n", person.getName());
    }
}
/*
output:
1 + 2 = 3
Person's name is Tom
 */
package com.company.app;

import com.company.app.models.Person;

public class App
{
    public static void main( String[] args )
    {
        Person tom = new Person();
        tom.setName("Tom");
        System.out.println(tom.getName());
    }
}

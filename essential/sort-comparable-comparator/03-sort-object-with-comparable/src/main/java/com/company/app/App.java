package com.company.app;

import java.util.Arrays;

/*
 Sort object with Comparable

 {Dick:8} {Tom:18} {Harry:28}
 */
public class App 
{
    static class Person implements Comparable<Person>{
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        public String name;
        public int age;
        public int compareTo(Person comparePerson) {
            return this.age - comparePerson.age;
        }
    }
    public static void main( String[] args )
    {
        Person[] people = new Person[3];
        people[0] = new Person("b", 2);
        people[1] = new Person("a", 1);
        people[2] = new Person("c", 3);
        Arrays.sort(people);
        for(Person person : people){
            System.out.print("{" + person.name + ":" + person.age +"} ");
        }
    }
}

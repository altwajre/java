package com.company.app;

import java.util.Arrays;
import java.util.Comparator;

/*
 To sort with multiple properties, we need Comparator.

 */
public class App 
{
    static class Person implements Comparable<Person>, Comparator<Person>{
        public Person(){}
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        public String name;
        public int age;
        public int compareTo(Person comparePerson) {
            return this.age - comparePerson.age;
        }
        public int compare(Person p1, Person p2) {
            String p1name = p1.name;
            String p2name = p2.name;
            return p1name.compareTo(p2name);
        }
        public static Comparator<Person> personNameComparator = new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                String p1name = p1.name;
                String p2name = p2.name;
                return p1name.compareTo(p2name);
            }
        };
    }
    static class PersonNameComparator implements Comparator<Person>{
        public int compare(Person p1, Person p2) {
            String p1name = p1.name;
            String p2name = p2.name;
            return p1name.compareTo(p2name);
        }
    }
    public static void main( String[] args )
    {
        compareWithComparable(); // sort age

        compareWithComparator();
    }

    private static void compareWithComparator() {
        separateComparatorClass();  // sort name: class PersonNameComparator implements Comparator<Person>

        personAsComparator();  // sort name: class Person implements Comparator<Person> and Comparable<Person>

        staticComparatorProperty(); // sort name: Person static personNameComparator property
    }

    private static void compareWithComparable() {
        System.out.print("Comparable: \n");
        Person[] people = new Person[5];
        people[0] = new Person("b", 2);
        people[1] = new Person("d", 1);
        people[2] = new Person("c", 3);
        people[3] = new Person("a", 2);
        people[4] = new Person("a", 1);
        Arrays.sort(people);
        for(Person person : people){
            System.out.print("{" + person.name + ":" + person.age +"} ");
        }
    }

    private static void separateComparatorClass() {
        System.out.print("\nSeparate Comparator Class: \n");
        Person[] people = new Person[5];
        people[0] = new Person("b", 2);
        people[1] = new Person("d", 1);
        people[2] = new Person("c", 3);
        people[3] = new Person("a", 2);
        people[4] = new Person("a", 1);
        Arrays.sort(people, new PersonNameComparator());
        for(Person person : people){
            System.out.print("{" + person.name + ":" + person.age +"} ");
        }
    }

    private static void personAsComparator() {
        System.out.print("\nPerson implements Comparator: \n");
        Person[] people = new Person[5];
        people[0] = new Person("b", 2);
        people[1] = new Person("d", 1);
        people[2] = new Person("c", 3);
        people[3] = new Person("a", 2);
        people[4] = new Person("a", 1);
        Arrays.sort(people, new Person());  // class Person implements Comparator<Person> and Comparable<Person>
        for(Person person : people){
            System.out.print("{" + person.name + ":" + person.age +"} ");
        }
    }

    private static void staticComparatorProperty() {
        System.out.print("\nStatic Comparator Property: \n");
        Person[] people = new Person[5];
        people[0] = new Person("b", 2);
        people[1] = new Person("d", 1);
        people[2] = new Person("c", 3);
        people[3] = new Person("a", 2);
        people[4] = new Person("a", 1);
        Arrays.sort(people, Person.personNameComparator);  // class Person implements Comparator<Person> and Comparable<Person>
        for(Person person : people){
            System.out.print("{" + person.name + ":" + person.age +"} ");
        }
    }
}

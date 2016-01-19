package com.company.app;

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
public class App
{
    static Person temp = null;
    static void passRef(Person person){
        temp = person;
    }
    static void modifyRef(Person person){
        person.setName("modified");
    }
    public static void main( String[] args )
    {
//        passReference();

        Person person = new Person("Dick", 20);
        modifyRef(person);
        System.out.println(person.getName());

    }

    private static void passReference() {
        Person person = new Person("Tom", 10);
        passRef(person);
        if(person == temp){
            System.out.println("pass by reference");
        }
        else{
            System.out.println("pass by value");
        }
    }
}

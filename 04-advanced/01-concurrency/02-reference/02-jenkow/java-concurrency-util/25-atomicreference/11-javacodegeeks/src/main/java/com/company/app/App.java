package com.company.app;

/*
http://examples.javacodegeeks.com/core-java/util/concurrent/atomic/atomicreference/java-atomicreference-example/

 */
class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    @Override
    public String toString(){
        return "[name " + this.name + ", age " + this.age + "]";
    }
}
public class App
{
    public static void main( String[] args )
    {
    }
}

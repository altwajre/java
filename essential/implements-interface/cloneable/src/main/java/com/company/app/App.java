package com.company.app;

public class App
{
    static class Person implements Cloneable{
        private String name;
        public Person(String name){
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public Person clone(){
            try{
                return (Person) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
    }
    public static void main( String[] args )
    {
        Person person = new Person("Tom");
        Person clonePerson = person.clone();
        System.out.println(clonePerson.getName());
    }
}

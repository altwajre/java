package com.company.app;

import java.lang.reflect.Constructor;

class Person{
    private String name = "unknown";
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
class Book{
    private String name = "unknown";
    public Book(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
public class App
{
    public static void main( String[] args )
    {
        defaultConstructor();

        constructorHasParam();
    }

    private static void constructorHasParam() {
        System.out.println("# constructorHasParam");
        try {
            Class<?> clazz = Class.forName("com.company.app.Book");
            Constructor<?> ctor = clazz.getConstructor(String.class);
            Object object = ctor.newInstance(new Object[]{"Tom"});
            System.out.println(object.getClass().getSimpleName());
            Book book = (Book)object;
            System.out.println("book name="+book.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void defaultConstructor() {
        System.out.println("# defaultConstructor");
        try {
            Class<?> clazz = Class.forName("com.company.app.Person");
            Object object = clazz.newInstance();
            System.out.println(object.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/*
output:
# defaultConstructor
Person
# constructorHasParam
Book
book name=Tom
 */

package com.company.app;

/*
https://www.journaldev.com/2752/java-8-interface-changes-static-method-default-method
For creating a default method in java interface, we need to use “default” keyword with the method signature.
 */
interface Interface1 {
    void method1(String str);
    default void log(String str) {
        System.out.println("Interface1 logging: " + str);
    }
}
interface Interface2 {
    void method2();
    default void log(String str) {
        System.out.println("Interface2 logging: " + str);
    }
}
class MyClass implements Interface1, Interface2 {

    @Override
    public void method1(String str) {

    }

    @Override
    public void method2() {

    }

    @Override
    public void log(String str) {
        System.out.println("MyClass logging: " + str);
        method1("abc");
    }
}
public class DefaultMethodApp
{
    public static void main( String[] args )
    {
        MyClass myClass = new MyClass();
        myClass.log("Tom");
    }
}
/*
output:
MyClass logging: Tom
 */

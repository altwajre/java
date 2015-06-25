package com.company.app;


import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main( String[] args ) throws Exception {
        personClass();

        object_getClass();
        dot_class();
        class_forName();

        TYPE_Filed_for_Primitive_Type_Wrappers();

        methods_Return_Classes();

        System.out.println("done");
    }

    private static void methods_Return_Classes() {
        Class customerSuperclass = Customer.class.getSuperclass();
        System.out.println(customerSuperclass);

        Class<?>[] charClasses = Character.class.getClasses();
        System.out.println(charClasses);

        Class<?>[] personClasses = Person.class.getClasses();
        System.out.println(personClasses);

        Class<?>[] c = Character.class.getDeclaredClasses();
        System.out.println(c);
    }

    private static void TYPE_Filed_for_Primitive_Type_Wrappers() {
        Class doubleClass = Double.TYPE;
        System.out.println(doubleClass);
        Class voidClass = Void.TYPE;
        System.out.println(voidClass);
        Class voidClass2 = void.class;
        System.out.println(voidClass2);
    }

    private static void class_forName() throws ClassNotFoundException {
        Class stringClass = Class.forName("java.lang.String");
        System.out.println(stringClass);

        Class hashSetClass = Class.forName("java.util.HashSet");
        System.out.println(hashSetClass);
    }

    private static void dot_class() {
        booleanClass();
        printStreamClass();
        arrayClass();
    }

    private static void arrayClass() {
        Class c = int[][][].class;
        System.out.println(c);
    }

    private static void object_getClass() {
        stringClass();
        enumClass();
        byteClass();
        hashSetClass();
    }

    private static void printStreamClass() {
        Class c = java.io.PrintStream.class;
        System.out.println(c);
    }

    private static void booleanClass() {
        Class c = boolean.class;
        System.out.println(c);
    }

    private static void personClass() throws ClassNotFoundException {
        Person tom = new Person();
        tom.setName("Tom");
        Class tomClass = tom.getClass();
        System.out.println(tomClass);

        Class c = Person.class;
        System.out.println(c);

        Class classForName = Class.forName("com.company.app.Person");
        System.out.println(classForName);
    }

    private static void hashSetClass() {
        Set<String> s = new HashSet<String>();
        Class c = s.getClass();
        System.out.println(c);
    }

    private static void byteClass() {
        byte[] bytes = new byte[1024];
        Class c = bytes.getClass();
        System.out.println(c);
    }

    private static void enumClass() {
        Class c = E.A.getClass();
        System.out.println(c);
    }

    private static void stringClass() {
        Class c = "foo".getClass();
        System.out.println(c);
        System.out.println(String.class);
    }
}

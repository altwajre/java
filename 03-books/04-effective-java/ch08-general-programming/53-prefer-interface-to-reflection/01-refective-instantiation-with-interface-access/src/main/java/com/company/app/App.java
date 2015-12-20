package com.company.app;

import java.util.Arrays;
import java.util.Set;

/*
Reflective instantiation with interface access

Two disadvantages:
This example demonstrates two disadvantages of reflection. First, the example can generate three runtime errors, all of
which would have been compile-time errors if reflective instantiation were not used. Second, it takes twenty lines of
code to generate an instance of the class from its name, whereas a constructor invocation would fit neatly on a single
line.

In summary, reflection is powerful, but it has many disadvantages. If you are writing a program that has to work with
class unknown at compile time, you should use reflection only to instantiate objects, and access the objects using some
interface or superclass that is known at compile time.
 */
public class App
{
    static String[] items = {"z", "y", "a", "d", "e"};
    public static void main( String[] args )
    {
        hashSetTest("java.util.HashSet");
        hashSetTest("java.util.TreeSet");
    }

    private static void hashSetTest(String className) {
        System.out.println("#test " + className);
        // Translate the class name into a Class object
        Class<?> cl = null;
        try {
            cl = Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found.");
            System.exit(1);
        }
        // Instantiate the class
        Set<String> s = null;
        try {
            s = (Set<String>) cl.newInstance();
        } catch (InstantiationException e) {
            System.err.println("Class not instantiable");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.err.println("Class not accessible");
            System.exit(1);
        }

        // Exercise the set
        s.addAll(Arrays.asList(items));
        System.out.println(s);
    }
}
/*
output:
#test java.util.HashSet
[a, d, e, y, z]
#test java.util.TreeSet
[a, d, e, y, z]
 */
package com.company.app;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Just Compile the program
Eliminate every unchecked warning that you can. If you eliminate all warnings, you are assured that your code is
typesafe.
If you can't eliminate a warning, and you can prove that the code that provoked the warning is typesafe, then suppress
the warning with an @SuppressWarnings("unchecked") annotation.
Always use the SuppressWarnings annotation on the smallest scope possible. Never use SuppressWarnings on an entire class.
It is illegal to put a SuppressWarnings annotation on the return statement because it isn't a declaration.
Every time you use an @SuppressWarnings("unchecked") annotation, add a comment saying why it's safe to do so.
 */
public class App<T>
{
    private int size;
    T[] elements;
    // Adding local variable to reduce scope of @SuppressWarnings
    public <T> T[] toArray(T[] a){
        if(a.length < size){
            // This cast is correct because the array we're creating is of the same type as the one passed in
            @SuppressWarnings("unchecked")
            T[] result = (T[]) Arrays.copyOf(elements, size, a.getClass());
            return result;
        }
        System.arraycopy(elements, 0, a, 0, size);
        if(a.length > size){
            a[size] = null;
        }
        return a;
    }
    public static void main( String[] args )
    {
        Set<String> strings = new HashSet(); // Compile warning: Information:java: uses unchecked or unsafe operations.
//        Set<String> strings = new HashSet<String>(); // Add type (String) parameter
    }
}

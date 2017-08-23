package com.company.app;

import java.util.Arrays;
import java.util.EmptyStackException;

class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }
    public Object pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        return elements[--size];  // Memory leak because pop without eliminate obsolete reference
//        Object result = elements[--size];
//        elements[size] = null;  // Eliminate obsolete reference
//        return result;
    }
    // Ensure space for at least one more element, roughly doubling the capacity each time the array needs to grow
    private void ensureCapacity(){
        if(elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}

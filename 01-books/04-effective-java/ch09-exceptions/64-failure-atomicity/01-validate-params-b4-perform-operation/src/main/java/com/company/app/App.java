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
        /**
         * Failure atomicity
         * Check parameters for validity before performing the operation. This causes any exception to get thrown before
         * object modification commences.
         *
         * If the initial size check were eliminated, the method would still throw an exception when it attempted to pop
         * an element from an empty stack. However, it would leave the size field in an inconsistent (negative) state,
         * causing any future method invocations on the object to fail. Additionally, the ArrayIndexOutOfBoundsException
         * is thrown instead of EmptyStackException.
         */
        if(size == 0){  // <- HERE is the validate params
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;  // Eliminate obsolete reference
        return result;
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
        int[] numbers = {1 ,2};
        try{
            System.out.println(numbers[2]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
        }
    }
}

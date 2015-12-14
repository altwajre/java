package com.company.app;

import java.util.Arrays;

class EmptyStackException extends RuntimeException{}
class Stack<E>{
    private Object[] elements; // HERE
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }
    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        E result = (E)elements[--size];
        elements[size] = null; // Eliminate obsolete reference
        return result;
    }
    public boolean isEmpty(){
        return size == 0;
    }
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
        String[] items = {"ab", "bc", "cd"};
        Stack<String> stack = new Stack<String>();
        for(String item : items){
            stack.push(item);
        }
        while(!stack.isEmpty()){
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
/*
output:
CD
BC
AB
 */

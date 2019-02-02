package com.company.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;

// without wildcard type - deficient
// If the element type exactly match, it works fine. But invoke push(intVal) where intVal is Integer type won't work.
// Compile error occurs
class Stack<E>{
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    // The elements array will contain only E instances from push(E).
    // This is sufficient to ensure type safety, but the runtime
    // type of the array won't be E[]; it will always be Object[]!
    @SuppressWarnings("unchecked")
    public Stack(){
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }
    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        E result = elements[--size];
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
    // pushAll method without wildcard type - deficient!
    public void pushAll(Iterable<E> src){
        for(E e : src){
            push(e);
        }
    }
    // popAll method without wildcard type - deficient!
    public void popAll(Collection<E> dst){
        while (!isEmpty()){
            dst.add(pop());
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Stack<Number> numberStack = new Stack<Number>();
        Iterable<Integer> integers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numberStack.pushAll(integers);
    }
}
/*
output:
Error:(57, 29) java: incompatible types: java.lang.Iterable<java.lang.Integer> cannot be converted to java.lang.Iterable<java.lang.Number>
 */

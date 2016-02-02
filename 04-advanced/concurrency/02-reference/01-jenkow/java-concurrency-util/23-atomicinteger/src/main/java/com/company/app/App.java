package com.company.app;

import java.util.concurrent.atomic.AtomicInteger;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomicinteger.html

The AtomicInteger class provides you with a int variable which can be read and written atomically, and which also
contains advanced atomic operations like compareAndSet().
 */
public class App
{
    public static void main( String[] args )
    {
        testGetValue();
        testSetValue();
        testGetAndSet();
        testCompareAndSet();
        testAdding();
    }

    private static void testAdding() {
        AtomicInteger atomicInteger = new AtomicInteger(8);
        int getFirstValue = atomicInteger.getAndAdd(10);
        System.out.println("#getFirstValue="+getFirstValue);
        int value = atomicInteger.get();
        System.out.println("#value="+value);

        int getLastValue = atomicInteger.addAndGet(10);
        System.out.println("#getLastValue="+getLastValue);
        value = atomicInteger.get();
        System.out.println("#value="+value);
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        AtomicInteger atomicInteger = new AtomicInteger(123);
        int expectedValue = 123;
        int newValue = 789;
        boolean wasNewValueSet = atomicInteger.compareAndSet(expectedValue, newValue);
        System.out.println("wasNewValueSet="+wasNewValueSet);
        int value = atomicInteger.get();
        System.out.println("value="+value);
    }

    private static void testGetAndSet() {
        System.out.println("#testGetAndSet");
        AtomicInteger atomicInteger = new AtomicInteger(123);
        int oldValue = atomicInteger.getAndSet(789);
        System.out.println("oldValue="+oldValue);
        int newValue = atomicInteger.get();
        System.out.println("newValue="+newValue);
    }

    private static void testSetValue() {
        System.out.println("#testSetValue");
        AtomicInteger atomicInteger = new AtomicInteger(123);
        atomicInteger.set(789);
        int value = atomicInteger.get();
        System.out.println("value="+value);
    }

    private static void testGetValue() {
        System.out.println("#testGetValue");
        AtomicInteger atomicInteger = new AtomicInteger(123);
        int value = atomicInteger.get();
        System.out.println("value="+value);
    }
}
/*
output:
#testGetValue
value=123
#testSetValue
value=789
#testGetAndSet
oldValue=123
newValue=789
#testCompareAndSet
wasNewValueSet=true
value=789
 */

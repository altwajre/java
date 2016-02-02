package com.company.app;

import java.util.concurrent.atomic.AtomicBoolean;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomicboolean.html

The AtomicBoolean class provides you with a boolean variable which can be read and written atomically, and which also
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
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        boolean expectedValue = true;
        boolean newValue = false;
        boolean wasNewValueSet = atomicBoolean.compareAndSet(expectedValue, newValue);
        System.out.println("wasNewValueSet="+wasNewValueSet);
        boolean value = atomicBoolean.get();
        System.out.println("value="+value);
    }

    private static void testGetAndSet() {
        System.out.println("#testGetAndSet");
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        boolean oldValue = atomicBoolean.getAndSet(false);
        System.out.println("oldValue="+oldValue);
        boolean newValue = atomicBoolean.get();
        System.out.println("newValue="+newValue);
    }

    private static void testSetValue() {
        System.out.println("#testSetValue");
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        atomicBoolean.set(false);
        boolean value = atomicBoolean.get();
        System.out.println("value="+value);
    }

    private static void testGetValue() {
        System.out.println("#testGetValue");
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        boolean value = atomicBoolean.get();
        System.out.println("value="+value);
    }
}
/*
output:
#testGetValue
value=true
#testSetValue
value=false
#testGetAndSet
oldValue=true
newValue=false
#testCompareAndSet
wasNewValueSet=true
value=false
 */

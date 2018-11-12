package com.company.app;

import java.util.concurrent.atomic.AtomicLong;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomiclong.html

The AtomicLong class provides you with a long variable which can be read and written atomically, and which also contains
advanced atomic operations like compareAndSet().
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
        System.out.println("#testAdding");
        AtomicLong atomicLong = new AtomicLong(8);
        long getFirstValue = atomicLong.getAndAdd(10);
        System.out.println("getFirstValue="+getFirstValue);
        long value = atomicLong.get();
        System.out.println("value="+value);

        long getLastValue = atomicLong.addAndGet(10);
        System.out.println("getLastValue="+getLastValue);
        value = atomicLong.get();
        System.out.println("value="+value);
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        AtomicLong atomicLong = new AtomicLong(123);
        long expectedValue = 123;
        long newValue = 789;
        boolean wasNewValueSet = atomicLong.compareAndSet(expectedValue, newValue);
        System.out.println("wasNewValueSet="+wasNewValueSet);
        long value = atomicLong.get();
        System.out.println("value="+value);
    }

    private static void testGetAndSet() {
        System.out.println("#testGetAndSet");
        AtomicLong atomicLong = new AtomicLong(123);
        long oldValue = atomicLong.getAndSet(789);
        System.out.println("oldValue="+oldValue);
        long newValue = atomicLong.get();
        System.out.println("newValue="+newValue);
    }

    private static void testSetValue() {
        System.out.println("#testSetValue");
        AtomicLong atomicLong = new AtomicLong(123);
        atomicLong.set(789);
        long value = atomicLong.get();
        System.out.println("value="+value);
    }

    private static void testGetValue() {
        System.out.println("#testGetValue");
        AtomicLong atomicLong = new AtomicLong(123);
        long value = atomicLong.get();
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
#testAdding
getFirstValue=8
value=18
getLastValue=28
value=28
 */

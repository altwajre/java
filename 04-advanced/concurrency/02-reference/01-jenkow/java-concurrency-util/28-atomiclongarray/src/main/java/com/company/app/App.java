package com.company.app;

import java.util.concurrent.atomic.AtomicLongArray;

public class App
{
    public static void main( String[] args )
    {
        testGet();
        testSet();
        testCompareAndSet();
        testAddAndGet();
        testGetAndAdd();
        testIncrementAndGet();
        testGetAndIncrement();
    }

    private static void testGetAndIncrement() {
        System.out.println("#testGetAndIncrement");
        long[] longs = new long[10];
        longs[5] = 1;
        AtomicLongArray array = new AtomicLongArray(longs);
        long value = array.getAndIncrement(5);
        System.out.println("value="+value);
    }

    private static void testIncrementAndGet() {
        System.out.println("#testIncrementAndGet");
        long[] longs = new long[10];
        longs[5] = 1;
        AtomicLongArray array = new AtomicLongArray(longs);
        long value = array.incrementAndGet(5);
        System.out.println("value="+value);
    }

    private static void testGetAndAdd() {
        System.out.println("#testGetAndAdd");
        long[] longs = new long[10];
        longs[5] = 1;
        AtomicLongArray array = new AtomicLongArray(longs);
        long value = array.getAndAdd(5, 8);
        System.out.println("value="+value);
    }

    private static void testAddAndGet() {
        System.out.println("#testAddAndGet");
        long[] longs = new long[10];
        longs[5] = 1;
        AtomicLongArray array = new AtomicLongArray(longs);
        long value = array.addAndGet(5, 8);
        System.out.println("value="+value);
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        long[] longs = new long[10];
        longs[5] = 123;
        AtomicLongArray array = new AtomicLongArray(longs);
        boolean swapped = array.compareAndSet(5, 123, 789);
        System.out.println("swapped="+swapped);
        long value = array.get(5);
        System.out.println("value="+value);
    }

    private static void testSet() {
        System.out.println("#testSet");
        long[] longs = new long[10];
        longs[5] = 123;
        AtomicLongArray array = new AtomicLongArray(longs);
        array.set(5, 999);
        long value = array.get(5);
        System.out.println("value="+value);
    }

    private static void testGet() {
        System.out.println("#testGet");
        long[] longs = new long[10];
        longs[5] = 123;
        AtomicLongArray array = new AtomicLongArray(longs);
        long value = array.get(5);
        System.out.println("value="+value);
    }
}
/*
output:
#testGet
value=123
#testSet
value=999
#testCompareAndSet
swapped=true
value=789
#testAddAndGet
value=9
#testGetAndAdd
value=1
#testIncrementAndGet
value=2
#testGetAndIncrement
value=1
 */

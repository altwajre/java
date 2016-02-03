package com.company.app;

import java.util.concurrent.atomic.AtomicIntegerArray;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomicintegerarray.html

 */
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
        int[] numbers = new int[10];
        numbers[5] = 1;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        int value = array.getAndIncrement(5);
        System.out.println("value="+value);
    }

    private static void testIncrementAndGet() {
        System.out.println("#testIncrementAndGet");
        int[] numbers = new int[10];
        numbers[5] = 1;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        int value = array.incrementAndGet(5);
        System.out.println("value="+value);
    }

    private static void testGetAndAdd() {
        System.out.println("#testGetAndAdd");
        int[] numbers = new int[10];
        numbers[5] = 1;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        int value = array.getAndAdd(5, 8);
        System.out.println("value="+value);
    }

    private static void testAddAndGet() {
        System.out.println("#testAddAndGet");
        int[] numbers = new int[10];
        numbers[5] = 1;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        int value = array.addAndGet(5, 8);
        System.out.println("value="+value);
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        int[] numbers = new int[10];
        numbers[5] = 123;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        boolean swapped = array.compareAndSet(5, 123, 789);
        System.out.println("swapped="+swapped);
        int value = array.get(5);
        System.out.println("value="+value);
    }

    private static void testSet() {
        System.out.println("#testSet");
        int[] numbers = new int[10];
        numbers[5] = 123;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        array.set(5, 999);
        int value = array.get(5);
        System.out.println("value="+value);
    }

    private static void testGet() {
        System.out.println("#testGet");
        int[] numbers = new int[10];
        numbers[5] = 123;
        AtomicIntegerArray array = new AtomicIntegerArray(numbers);
        int value = array.get(5);
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

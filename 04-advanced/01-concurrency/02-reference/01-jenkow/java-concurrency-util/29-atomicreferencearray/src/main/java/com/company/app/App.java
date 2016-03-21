package com.company.app;

import java.util.concurrent.atomic.AtomicReferenceArray;

/*
http://tutorials.jenkov.com/java-util-concurrent/atomicreferencearray.html

 */
public class App
{
    public static void main( String[] args )
    {
        testGet();
        testSet();
        testCompareAndSet();
    }

    private static void testCompareAndSet() {
        System.out.println("#testCompareAndSet");
        String string1 = "string1";
        String string2 = "string2";
        String[] source = new String[10];
        source[5] = string1;
        AtomicReferenceArray<String> array = new AtomicReferenceArray<String>(source);
        boolean wasNewValueSet = array.compareAndSet(5, string1, string2);
        System.out.println("wasNewValueSet="+wasNewValueSet);
        String element = array.get(5);
        System.out.println("element="+element);
    }

    private static void testSet() {
        System.out.println("#testSet");
        String[] source = new String[10];
        source[5] = "Some string";
        AtomicReferenceArray<String> array = new AtomicReferenceArray<String>(source);
        array.set(5, "another object");
        String element = array.get(5);
        System.out.println("element="+element);
    }

    private static void testGet() {
        System.out.println("#testGet");
        String[] source = new String[10];
        source[5] = "Some string";
        AtomicReferenceArray<String> array = new AtomicReferenceArray<String>(source);
        String element = array.get(5);
        System.out.println("element="+element);
    }
}
/*
output:
#testGet
element=Some string
#testSet
element=another object
#testCompareAndSet
wasNewValueSet=true
element=string2
 */

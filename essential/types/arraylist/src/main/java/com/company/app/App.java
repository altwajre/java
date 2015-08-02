package com.company.app;

import java.util.ArrayList;

/*
 */
public class App 
{
    public static void main( String[] args )
    {
        testIndex();
        testArrayListAddAll();
    }

    private static void testIndex() {
        System.out.println("#testIndex");
        int count = 3;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < count; i++) list.add(i);
        System.out.println("index 0: " + list.get(0));
        System.out.println("index 1: " + list.get(1));
        System.out.println("index 2: " + list.get(2));
    }

    private static void testArrayListAddAll() {
        System.out.println("#testArrayListAddAll");
        ArrayList<Integer> all = new ArrayList<Integer>();
        int count = 3;
        for(int i = 0; i < count; i++) all.add(i);

        ArrayList<Integer> subset = new ArrayList<Integer>();
        for(int i = 0; i < count; i++) all.add(i+10);

        all.addAll(subset);

        System.out.println(all);
    }
}

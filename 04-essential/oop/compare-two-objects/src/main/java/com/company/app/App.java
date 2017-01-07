package com.company.app;

public class App
{

    public static void main( String[] args )
    {
        compareReferences();

        compareValues();
    }

    private static void compareReferences() {
        System.out.println("#compareReferences");
        String obj1 = new String("xyz");
        String obj2 = new String("xyz");
        String obj3 = obj1;
        if(obj1 == obj2) System.out.println("obj1==obj2 is TRUE");
        else System.out.println("obj1==obj2 is FALSE");
        if(obj1 == obj3) System.out.println("obj1==obj3 is TRUE");
        else System.out.println("obj1==obj3 is FALSE");
    }

    private static void compareValues() {
        System.out.println("#compareValues");
        String obj1 = new String("xyz");
        String obj2 = new String("xyz");
        if(obj1.equals(obj2)) System.out.println("obj1.equals(obj2) is TRUE");
        else System.out.println("obj1.equals(obj2) is FALSE");
    }
}

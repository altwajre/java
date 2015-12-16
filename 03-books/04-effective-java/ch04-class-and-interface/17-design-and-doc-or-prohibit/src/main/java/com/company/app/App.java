package com.company.app;

import java.util.Date;

class Super{
    // Broken - constructor invokes an overridable method
    public Super(){
        overrideMe();
    }
    public void overrideMe(){
    }
}
final class Sub extends Super{
    private final Date date; // Blank final, set by constructor
    Sub() {
        this.date = new Date();
    }
    // Overriding method invoked by superclass constructor
    @Override
    public void overrideMe(){
        // It's invoked by Super constructor before Sub constructor has a chance to initialize the data field
//        date.toString(); // NullPointerException
        System.out.println(date); // print null because println method has special provisions to deal with null arg
    }
}
public class App
{
    public static void main( String[] args )
    {
        Sub sub = new Sub();
        sub.overrideMe();
    }
}
/*
output:
null
Tue Dec 15 17:29:56 PST 2015
 */

package com.company.app;

/*
 */
public class App 
{
    public static void main( String[] args )
    {
        String msg = "Hi, Tom";
        int index = 2;
        String before = msg.substring(0, index);  // Hi
        System.out.println(before + ":" + before.length());
        String after = msg.substring(index);  // ", Tom"
        System.out.println(after + ":" + after.length());
    }
}

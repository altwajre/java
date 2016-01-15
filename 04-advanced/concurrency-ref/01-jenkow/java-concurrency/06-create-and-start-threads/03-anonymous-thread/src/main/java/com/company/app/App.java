package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Anonymous subclass thread running");
            }
        };
        thread.start();
    }
}
/*
output:
Anonymous subclass thread running
 */

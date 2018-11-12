package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        String threadName = "Anonymous Subclass";
        Thread thread = new Thread(threadName){
            public void run(){
                System.out.println("Thread name: " + getName());
            }
        };
        thread.start();
    }
}
/*
output:
Thread name: Anonymous Subclass
 */


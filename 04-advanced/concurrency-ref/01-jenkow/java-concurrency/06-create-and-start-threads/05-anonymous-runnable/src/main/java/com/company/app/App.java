package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        Runnable myRunnable = new Runnable() {
            public void run() {
                System.out.println("Anonymous Runnable running");
            }
        };
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
/*
output:
Anonymous Runnable running
 */


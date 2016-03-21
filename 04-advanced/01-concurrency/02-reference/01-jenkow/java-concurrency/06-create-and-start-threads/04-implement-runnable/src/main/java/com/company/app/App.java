package com.company.app;

class MyRunnable implements Runnable{
    public void run() {
        System.out.println("MyRunnable running");
        String threadName = Thread.currentThread().getName();
        System.out.println("MyRunnable thread name: " + threadName);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }
}
/*
output:
MyRunnable running
MyRunnable thread name: Thread-0
 */

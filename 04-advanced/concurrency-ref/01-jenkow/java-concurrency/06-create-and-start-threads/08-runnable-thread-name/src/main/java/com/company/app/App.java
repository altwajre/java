package com.company.app;

/*
get runnable Thread thread-name
 */
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
        String threadName = "MyRunnable";
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, threadName);
        thread.start();
        System.out.println("Thread name: " + thread.getName());
    }
}
/*
output:
Thread name: MyRunnable
MyRunnable running
MyRunnable thread name: MyRunnable
 */
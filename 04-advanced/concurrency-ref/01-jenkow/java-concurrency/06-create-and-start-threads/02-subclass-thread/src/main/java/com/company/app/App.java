package com.company.app;

class MyThread extends Thread{
    public MyThread(){}
    public MyThread(String threadName){
        super(threadName);
    }
    @Override
    public void run(){
        System.out.println("MyThread running");
        String threadName = Thread.currentThread().getName();
        System.out.println("MyThread thread name: " + threadName);
    }
}
public class App
{
    public static void main( String[] args )
    {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
/*
outpout:
MyThread running
MyThread thread name: Thread-0
 */

package com.company.app;

/*
get subclass Thread thread-name
 */
class MyThread extends Thread{
    public MyThread(){}
    public MyThread(String threadName){
        super(threadName);
    }
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
        String threadName = "MyThread";
        MyThread myThread = new MyThread(threadName);
        myThread.start();
    }
}
/*
output:
MyThread running
MyThread thread name: MyThread
 */

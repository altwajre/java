package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/creating-and-starting-threads.html

 */
public class App
{
    static class MyThread extends Thread{
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
    static class MyRunnable implements Runnable{
        public void run() {
            System.out.println("MyRunnable running");
            String threadName = Thread.currentThread().getName();
            System.out.println("MyRunnable thread name: " + threadName);
        }
    }

    // Tests
    private static void myThreadThreadNameTest() {
        String threadName = "MyThread";
        MyThread myThread = new MyThread(threadName);
        myThread.start();
    }
    private static void myRunnableThreadNameTest() {
        String threadName = "MyRunnable";
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, threadName);
        thread.start();
        System.out.println("Thread name: " + thread.getName());
    }

    public static void main( String[] args )
    {
        currentThreadTest(); // main thread name: main

        threadSubclassTest();  // MyThread running; MyThread thread name: Thread-0
        anonymousSubclassTest(); // Anonymous subclass thread running

        implementRunnableInterfaceTest(); // MyRunnable running; MyRunnable thread name: Thread-0
        anonymousRunnableTest(); // Anonymous Runnable running

        anonymousSubclassThreadNameTest(); // Thread name: Anonymous Subclass

        /*
        output:
        MyThread running
        MyThread thread name: MyThread
         */
        myThreadThreadNameTest();

        /*
        output:
        Thread name: MyRunnable
        MyRunnable running
        MyRunnable thread name: MyRunnable
         */
        myRunnableThreadNameTest();

        /*
        output:
        main
        Thread: 0 running
        Thread: 1 running
        Thread: 2 running
        Thread: 3 running
        Thread: 4 running
        Thread: 5 running
        Thread: 6 running
        Thread: 7 running
        Thread: 8 running
        Thread: 9 running
         */
        threadExampleTest();
    }

    private static void anonymousSubclassThreadNameTest() {
        String threadName = "Anonymous Subclass";
        Thread thread = new Thread(threadName){
            public void run(){
                System.out.println("Thread name: " + getName());
            }
        };
        thread.start();
    }

    private static void anonymousRunnableTest() {
        Runnable myRunnable = new Runnable() {
            public void run() {
                System.out.println("Anonymous Runnable running");
            }
        };
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    private static void threadExampleTest() {
        System.out.println(Thread.currentThread().getName());
        for(int i = 0; i < 10; i++){
            new Thread("" + i){
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }

    private static void currentThreadTest() {
        System.out.println("main thread name: " + Thread.currentThread().getName());
    }

    private static void implementRunnableInterfaceTest() {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }

    private static void anonymousSubclassTest() {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Anonymous subclass thread running");
            }
        };
        thread.start();
    }

    private static void threadSubclassTest() {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

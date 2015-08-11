package com.company.app;

public class App
{
    static class MyThread extends Thread{
        public void run(){
            System.out.println("MyThread running");
        }
    }
    static class MyRunnable implements Runnable{
        public void run() {
            System.out.println("MyRunnable running");
            String threadName = Thread.currentThread().getName();
            System.out.println("MyRunnable thread name: " + threadName);
        }
    }
    public static void main( String[] args )
    {
//        threadSubclassTest();  // MyThread running
//        anonymousSubclassTest(); // Anonymous subclass thread Running
//
//        implementRunnableInterfaceTest(); // MyRunnable running; MyRunnable thread name: Thread-0
//        anonymousRunnableTest(); // Anonymous Runnable running
//
//        anonymousSubclassThreadNameTest(); // Thread name: Anonymous Subclass

        /*
        output:
        Thread name: Anonymous Runnable
        MyRunnable running
        MyRunnable thread name: Anonymous Runnable
         */
//        anonymouseRunnableThreadNameTest();
//
//        currentThreadTest(); // main thread name: main

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

    private static void anonymouseRunnableThreadNameTest() {
        String threadName = "Anonymous Runnable";
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, threadName);
        thread.start();
        System.out.println("Thread name: " + thread.getName());
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

    private static void implementRunnableInterfaceTest() {
        Thread thread = new Thread(new MyRunnable());
        thread.start();
    }

    private static void anonymousSubclassTest() {
        Thread thread = new Thread(){
            public void run(){
                System.out.println("Anonymous subclass thread Running");
            }
        };
        thread.start();
    }

    private static void threadSubclassTest() {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

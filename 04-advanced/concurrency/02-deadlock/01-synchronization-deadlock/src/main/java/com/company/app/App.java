package com.company.app;

/*
http://www.javaworld.com/article/2075692/java-concurrency/avoid-synchronization-deadlocks.html
synchronization deadlock

output: DEADLOCK
method1: lock1 is locked
method2: lock2 is locked
method2: try to lock lock1
method1: try to lock lock2

 */
public class App 
{
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    static void method1() {
        synchronized (lock1){
            System.out.println("method1: lock1 is locked");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            System.out.println("method1: try to lock lock2");
            synchronized (lock2){
                System.out.println("method1 do something");
            }
            System.out.println("lock2 is unlocked");
        }
        System.out.println("lock1 is unlocked");
    }
    static void method2(){
        synchronized (lock2){
            System.out.println("method2: lock2 is locked");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            System.out.println("method2: try to lock lock1");
            synchronized (lock1){
                System.out.println("method2 do something");
            }
            System.out.println("lock1 is unlocked");
        }
        System.out.println("lock2 is unlocked");
    }
    public static void main( String[] args )
    {
        Thread thread1 = new Thread(){
            public void run(){
                method1();
            }
        };
        thread1.start();

        Thread thread2 = new Thread(){
            public void run(){
                method2();
            }
        };
        thread2.start();
    }
}

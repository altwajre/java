package com.company.app;

/*
http://www.javaworld.com/article/2075692/java-concurrency/avoid-synchronization-deadlocks.html
synchronization deadlock

a deadlock occurs when two threads obtain locks in different order.
Thread1 locks lock1 and waits for lock2
Thread2 locks lock2 and waits for lock1

In deadlock, two threads are waiting for each other to release locks.

output: DEADLOCK
Thread_1 locks lock1
Thread_2 locks lock2
Thread_2 waits lock1
Thread_1 waits lock2

 */
public class App 
{
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    static void method1() {
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " locks lock1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " waits lock2");
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " do something");
            }
            System.out.println("lock2 is unlocked");
        }
        System.out.println("lock1 is unlocked");
    }
    static void method2(){
        synchronized (lock2){
            System.out.println(Thread.currentThread().getName() + " locks lock2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " waits lock1");
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + " do something");
            }
            System.out.println("lock1 is unlocked");
        }
        System.out.println("lock2 is unlocked");
    }
    public static void main( String[] args )
    {
        Thread thread1 = new Thread("Thread_1"){
            public void run(){
                method1();
            }
        };
        thread1.start();

        Thread thread2 = new Thread("Thread_2"){
            public void run(){
                method2();
            }
        };
        thread2.start();
    }
}

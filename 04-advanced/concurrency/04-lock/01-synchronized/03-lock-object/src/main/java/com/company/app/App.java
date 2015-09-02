package com.company.app;

/*
When you want to synchronize only one variable at a time, so two threads won't block each other while accessing
different variables, you can synchronize them separately.

output below shows Thread_B first because Thread_A locks a separate variable.

output:
Thread_B lock2
Thread_A lock1

 */
public class App
{
    static class Lock {
        Object obj1 = new Object();
        Object obj2 = new Object();
        public void lock1(){
            synchronized (obj1){  // SYNC obj1
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " lock1");
            }
        }

        public void lock2(){
            synchronized (obj2){  // SYNC obj2
                System.out.println(Thread.currentThread().getName() + " lock2");
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();

        new Thread("Thread_A"){
            public void run(){
                lock.lock1();
            }
        }.start();

        new Thread("Thread_B"){
            public void run(){
                lock.lock2();
            }
        }.start();
    }
}

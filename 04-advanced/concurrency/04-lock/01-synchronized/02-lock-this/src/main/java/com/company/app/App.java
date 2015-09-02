package com.company.app;

/*
When you "synchronized (this)" or "synchronized void lock()", you lock the whole object.
Only one thread can access "Lock.lock1() synchronized (this) block" or "Lock.lock2()" at a time.

output below shows Thread_A first even it is slow than Thread_B because Thread_A locks "this" which
is the Lock object itself that will block other threads to access "Lock.lock2()"

output:
Thread_A lock1
Thread_B lock2

 */
public class App 
{
    static class Lock {
        public void lock1(){
            synchronized (this){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " lock1");
            }
        }

        public synchronized void lock2(){
            System.out.println(Thread.currentThread().getName() + " lock2");
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

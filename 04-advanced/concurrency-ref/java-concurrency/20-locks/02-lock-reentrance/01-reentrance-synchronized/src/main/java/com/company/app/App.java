package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/locks.html

Notice how both outer() and inner() are declared synchronized, which is Java is equivalent to a synchronized(this) block.
If a thread calls outer() there is no problem calling inner() from inside outer(), since both methods (or blocks)
are synchronized on the same monitor object (“this”). If a thread already holds the lock on a monitor object,
it has access to all blocks synchronized on the same monitor object. This is called reentrance. The thread can reenter
any block of code for which it already holds the lock.

output:
Thread_1 outer
Thread_1 inner
Thread_3 outer
Thread_3 inner
Thread_2 outer
Thread_2 inner

 */
public class App 
{
    static class Reentrant{
        public synchronized void outer(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " outer");
            inner();
        }
        public synchronized void inner(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " inner");
        }
    }
    public static void main( String[] args )
    {
        final Reentrant reentrant = new Reentrant();
        int threadCount = 3;
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_" + i){
                public void run(){
                    reentrant.outer();
                }
            }.start();
        }
    }
}

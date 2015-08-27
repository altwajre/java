package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/locks.html

Problem:
A thread calling outer() will first lock the Lock instance. Then it will call inner().
Inside the inner() method the thread will again try to lock the Lock instance.
This will fail (meaning the thread will be blocked), since the Lock instance was locked
already in the outer() method.

The reason the thread will be blocked the second time it calls lock() without calling unlock()
in between, is apparent when we look at the lock() implementation.

It is the condition inside the while loop (spin lock) that determines if a thread is allowed
to exit the lock() method or not. Currently the condition is that isLocked must be false for
this to be allowed, regardless of what thread locked it.

output:
Thread_1 outer

 */
public class App 
{
    static class Lock{
        boolean isLocked = false;
        public synchronized void lock(){
            while(isLocked){
                try { wait();
                } catch (InterruptedException e) { }
            }
            isLocked = true;
        }
        public synchronized void unlock(){
            notify();
            isLocked = false;
        }
    }
    static class Reentrant{
        Lock lock = new Lock();
        public void outer(){
            lock.lock();
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " outer");
            inner();
            lock.unlock();
        }
        public void inner() {
            lock.lock();
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " inner");
            lock.unlock();
        }
    }
    public static void main( String[] args )
    {
        final Reentrant reentrant = new Reentrant();
        new Thread("Thread_" + 1){
            public void run(){
                reentrant.outer();
            }
        }.start();

//        int threadCount = 3;
//        for(int i = 1; i <= threadCount; i++){
//            new Thread("Thread_" + i){
//                public void run(){
//                    reentrant.outer();
//                }
//            }.start();
//        }
    }
}

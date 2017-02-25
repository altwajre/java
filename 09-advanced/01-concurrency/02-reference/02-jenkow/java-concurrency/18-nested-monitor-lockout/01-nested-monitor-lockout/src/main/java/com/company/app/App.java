package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/nested-monitor-lockout.html

Problem: nested monitor lockout occurs
1, in lock(), the inner monitorObject.wait() is holding the synchronization lock on "this" object,
   and it is waiting for monitorObject.notify()
2, in unlock(), no thread can enter synchronized(this) because "this" object is locked by lock()

A nested monitor lockout occurs by two threads taking the locks in the same order.
Thread_1 is holding a lock_A, and waits for a signal from Thread_2.
Thread_2 needs the lock_A to send the signal to Thread_1.

output:
Thread_A_1 wait

 */
public class App 
{
    static int count = 0;
    static class Counter{
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("  " + Thread.currentThread().getName() + " increment end");
        }
    }
    static class Lock{
        Object monitorObject = new Object();
        boolean isLocked = false;
        public void lock(){
            synchronized(this){
                while(isLocked){
                    synchronized(this.monitorObject){  // PROBLEM: it is holding the synchronization lock on "this"
                        try {
                            System.out.println(Thread.currentThread().getName() + " wait");
                            this.monitorObject.wait();
                        } catch (InterruptedException e) { }
                    }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized(this){
                this.isLocked = false;
                synchronized(this.monitorObject){
                    System.out.println(" " + Thread.currentThread().getName() + " notify");
                    this.monitorObject.notify();
                }
            }
        }
    }
    public static void main( String[] args )
    {
        int threadCount = 3;
        final Counter counter = new Counter();
        for(int i =0; i < threadCount; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
        for(int i =0; i < threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
    }
}

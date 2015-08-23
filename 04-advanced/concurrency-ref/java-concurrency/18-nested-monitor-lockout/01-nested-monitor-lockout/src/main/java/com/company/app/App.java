package com.company.app;

/*
Problem: nested monitor lockout occurs
1, in lock(), the inner monitorObject.wait() is holding the synchronization lock on "this" object,
   and it is waiting for monitorObject.notify()
2, in unlock(), no thread can enter synchronized(this) because "this" object is locked by lock()

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

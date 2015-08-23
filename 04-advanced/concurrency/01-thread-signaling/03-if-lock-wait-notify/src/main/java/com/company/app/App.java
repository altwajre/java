package com.company.app;

/*
Problem:
1, if a thread calls notify() before the thread calls wait(), the waiting thread will miss signal,
   so waiting thread may waiting forever.

Solution:
1, to avoid losing signals, add a member variable isLocked in the Lock, so a thread will only call wait()
   when isLocked=false ensure notify() will happen after wait()

output:
lock
unlock
unlock finished
lock finished

 */
public class App 
{
    static class Lock {
        boolean isLocked = false;
        public void lock() throws InterruptedException {
            synchronized (this){
                if(!isLocked){
                    wait();
                }
                isLocked = false; // Clear the lock signal and continue running
            }
        }
        public void unlock(){
            synchronized (this){
                notify();
                isLocked = true;
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        Thread lockThread = new Thread(){
            public void run(){
                try {
                    System.out.println("lock");
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock finished");
            }
        };
        lockThread.start();

        Thread unlockThread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("unlock");
                lock.unlock();
                System.out.println("unlock finished");
            }
        };
        unlockThread.start();
    }
}

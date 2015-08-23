package com.company.app;

/*
A lock-thread locks the Lock, and an unlock-thread unlock the Lock

output:
lock
unlock
unlockThread finished
lockThread finished

 */
public class App 
{
    static class Lock {
        public void lock() throws InterruptedException {
            synchronized (this){
                wait();
            }
        }
        public void unlock(){
            synchronized (this){
                notify();
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        Thread lockThread = new Thread(){
            public void run(){
                System.out.println("lock");
                try {
                    lock.lock();
                } catch (InterruptedException e) { }
                System.out.println("lockThread finished");
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
                System.out.println("unlockThread finished");
            }
        };
        unlockThread.start();
    }
}

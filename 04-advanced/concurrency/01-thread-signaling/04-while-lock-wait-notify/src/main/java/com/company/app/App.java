package com.company.app;

/*
while loop is a spin lock

Problem: Spurious Wakeups
1, it is possible for threads to wake up without notify()

Solution:
1, use while loop instead of if-statement to guard against spurious wakeup to let go of the waiting thread

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
                while(!isLocked){ // NOTE: spin lock
                    wait();
                }
                isLocked = false;
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
        Thread waitThread = new Thread(){
            public void run(){
                System.out.println("lock");
                try {
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("lock finished");
            }
        };
        waitThread.start();

        Thread notifyThread = new Thread(){
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
        notifyThread.start();
    }
}

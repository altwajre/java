package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/starvation-and-fairness.html

Problem: the current version of the Lock class makes no guarantees about what thread is being granted access
         if more than one thread is waiting to enter.

output:
 Thread_1 finish
 Thread_5 wait()
 Thread_4 wait()
 Thread_3 wait()
 Thread_2 wait()
 Thread_1 notify();
 Thread_5 finish
 Thread_5 notify();
 Thread_4 finish
 Thread_4 notify();
 Thread_3 finish
 Thread_3 notify();
 Thread_2 finish
 Thread_2 notify();

 */
public class App
{
    static long duration = 2000;
    static int count = 1;
    static class Synchronizer {
        Lock lock = new Lock();
        public void doSynchronized() throws InterruptedException {
            this.lock.lock();
            // do a lot of work which takes a long time
            if(count == 3) Thread.sleep(3000);
            else Thread.sleep(duration);
            count++;
            this.lock.unlock();
        }
    }
    static class Lock{
        private boolean isLocked = false;
        private Thread lockingThread = null;
        public synchronized void lock() throws InterruptedException {
            String threadName = Thread.currentThread().getName();
            while(isLocked){
                System.out.println(" " + threadName + " wait()");
                wait();
            }
            isLocked = true;
            lockingThread = Thread.currentThread();
            System.out.println(" " + threadName + " finish");
        }
        public synchronized void unlock(){
            String threadName = Thread.currentThread().getName();
            if(this.lockingThread != Thread.currentThread()) {
                throw new IllegalMonitorStateException("Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            notify();
            System.out.println(" " + threadName + " notify();");
        }
    }
    public static void main( String[] args ) {
        final Synchronizer synchronizer = new Synchronizer();
        for(int i = 1; i <= 5; i++){
            String threadName = "Thread_" + i;
            new Thread(threadName){
                public void run(){
                    try {
                        synchronizer.doSynchronized();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}

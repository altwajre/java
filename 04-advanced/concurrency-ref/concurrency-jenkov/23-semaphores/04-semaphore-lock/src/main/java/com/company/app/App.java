package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/semaphores.html

Set the upper bound to 1 to use a bounded semaphore as a lock.

output:
Thread_1 start working
Thread_1 finished work
Thread_2 start working
Thread_2 finished work
Thread_4 start working
Thread_4 finished work
Thread_3 start working
Thread_3 finished work
Thread_5 start working
Thread_5 finished work

 */
public class App 
{
    static class BoundedSemaphore {
        public int signals = 0;
        public int bound = 0;
        public BoundedSemaphore(int upperBound){
            this.bound = upperBound;
        }
        public synchronized void take(){
            while (signals == bound){
                try { wait();
                } catch (InterruptedException e) { }
            }
            signals++;
            notify();
        }
        public synchronized void release(){
            while(signals == 0){
                try {
                    wait();
                } catch (InterruptedException e) { }
            }
            signals--;
            notify();
        }
    }
    public static void main( String[] args )
    {
        // NOTE: set the upper bound to 1 to make semaphore work as lock
        final BoundedSemaphore semaphore = new BoundedSemaphore(1);
        for(int i = 1; i <= 5; i++){
            new Thread("Thread_" + i){
                public void run(){
                    try{
                        semaphore.take();
                        try {
                            System.out.println(Thread.currentThread().getName() + " start working");
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + " finished work");
                        } catch (InterruptedException e) { }
                    }
                    finally {
                        semaphore.release();
                    }
                }
            }.start();
        }
    }
}

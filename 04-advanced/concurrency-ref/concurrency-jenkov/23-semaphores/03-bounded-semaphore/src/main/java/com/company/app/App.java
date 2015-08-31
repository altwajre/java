package com.company.app;

/*
TODO: figure out how to use BoundedSemaphore
checkout http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

 */
public class App 
{
    static class BoundedSemaphore{
        public int signals = 0;
        public int bound = 0;
        public BoundedSemaphore(int upperBound){ this.bound = upperBound; }
        public synchronized void take() {
            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + ": in take(); Semaphore.signals=" + signals);
            while(this.signals == bound){
                try {
                    System.out.println(threadName + ": wait(); Semaphore.signals=" + signals);
                    wait();
                } catch (InterruptedException e) { }
            }
            signals++;
            System.out.println(threadName + ": notify(); Semaphore.signals=" + signals);
            notify();
        }
        public synchronized void release() {
            String threadName = Thread.currentThread().getName();
//            System.out.println("  "+threadName + ": in release(); Semaphore.signals=" + signals);
            while(signals == 0){
                try {
                    System.out.println("  "+threadName + ": wait(); Semaphore.signals=" + signals);
                    wait();
                } catch (InterruptedException e) {  }
            }
            signals--;
            System.out.println("  "+threadName + ": notify(); Semaphore.signals=" + signals);
            notify();
        }
    }
    public static void main( String[] args )
    {
        final BoundedSemaphore semaphore = new BoundedSemaphore(2);
        for(int i = 1; i <= 5; i++){
            Thread takeThread = new Thread("Thread_Take_" + i){
                public void run(){
                    String threadName = Thread.currentThread().getName();
                    try {
                        Thread.sleep(100);
                        System.out.println(threadName + ": semaphore.take(); Semaphore.signals=" + semaphore.signals);
                    } catch (InterruptedException e) {  }
                    semaphore.take();
//                    try {
////                        System.out.println(threadName + ": do something; Semaphore.signals=" + semaphore.signals);
//                        Thread.sleep(2000);
//                        System.out.println(threadName + ": finished work; Semaphore.signals=" + semaphore.signals);
//                    } catch (InterruptedException e) {  }
                }
            };
            takeThread.start();
        }
        for(int i = 1; i <= 5; i++){
            Thread releaseThread = new Thread("Thread_Release_" + i){
                public void run(){
                    String threadName = Thread.currentThread().getName();
                    semaphore.release();
                    try {
//                        System.out.println("  "+threadName + ": do something; Semaphore.signals=" + semaphore.signals);
                        Thread.sleep(1000);
                        System.out.println("  " + threadName + ": finished work; Semaphore.signals=" + semaphore.signals);
                    } catch (InterruptedException e) { }
                }
            };
            releaseThread.start();
        }

    }
}

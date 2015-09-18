package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
Two threads signaling each other using a Semaphore

output:
Thread_Stoplight: stoplight turns green
Thread_Car: car passed stoplight

 */
public class App 
{
    static class Semaphore{
        public int signals = 0;
        public int upperBound = 0;
        public Semaphore(int upperBound){
            this.upperBound = upperBound;
        }
        public synchronized void take(){
            while(signals == upperBound){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            signals++;
            notify();
        }
        public synchronized void release() {
            while(signals == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            signals--;
            notify();
        }
    }
    public static void main( String[] args )
    {
        Semaphore semaphore = new Semaphore(1);

        new Thread("Thread_Car"){
            public void run(){
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + ": car passed stoplight");
            }
        }.start();

        new Thread("Thread_Stoplight"){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaphore.take();
                System.out.println(Thread.currentThread().getName() + ": stoplight turns green");
            }
        }.start();
    }
}

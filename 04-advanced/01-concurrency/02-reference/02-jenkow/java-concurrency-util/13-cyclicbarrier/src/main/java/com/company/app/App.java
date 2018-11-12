package com.company.app;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
http://tutorials.jenkov.com/java-util-concurrent/cyclicbarrier.html

It is synchronization mechanism that can synchronize threads progressing through some algorithm. It is a barrier that
all threads must wait at, until all threads reach it, before any of the threads can continue.
 */
class CyclicBarrierRunnable implements Runnable{
    CyclicBarrier barrier1 = null;
    CyclicBarrier barrier2 = null;
    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2){
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
            this.barrier1.await();

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
            this.barrier2.await();

            System.out.println(Thread.currentThread().getName() + " done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Runnable barrier1Action = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 1 executed ");
            }
        };
        Runnable barrier2Action = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 2 executed ");
            }
        };

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }
}
/*
output:
Thread-1 waiting at barrier 1
Thread-0 waiting at barrier 1
BarrierAction 1 executed
Thread-0 waiting at barrier 2
Thread-1 waiting at barrier 2
BarrierAction 2 executed
Thread-1 done!
Thread-0 done!
 */

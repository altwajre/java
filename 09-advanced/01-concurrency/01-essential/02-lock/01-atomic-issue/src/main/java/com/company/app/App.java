package com.company.app;

import java.util.concurrent.atomic.AtomicInteger;

/*
Atomic does not block the long run method to ensure client code get the correct state after each method call.
It only guarantees that Atomic variable can be read read and written atomically.
 */
class Counter{
    public AtomicInteger count = new AtomicInteger(0);
    public void increase(){
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        count.incrementAndGet();  // atomic increment
    }
}
public class App
{
    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        for(int i = 1; i <= 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    counter.increase();
                    System.out.println(Thread.currentThread().getName() + " - count: " + counter.count.get());
                }
            }.start();
        }
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("counter.count: " + counter.count);
    }
}
/*
output:
Thread_4 - count: 10
Thread_9 - count: 10
Thread_2 - count: 10
Thread_6 - count: 10
Thread_7 - count: 10
Thread_8 - count: 10
Thread_5 - count: 10
Thread_1 - count: 10
Thread_10 - count: 10
Thread_3 - count: 10
counter.count: 10
 */
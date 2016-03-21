package com.company.app;

/*
Thread safe

synchronized will block the threads, only one thread can access a time.
 */
class Counter{
    public int count = 0;
    public synchronized void increase(){
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        count++;
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
                    System.out.println(Thread.currentThread().getName() + " - count: " + counter.count);
                }
            }.start();
        }
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("counter.count: " + counter.count);
    }
}
/*
output:
Thread_1 - count: 1
Thread_10 - count: 2
Thread_9 - count: 3
Thread_8 - count: 4
Thread_7 - count: 5
Thread_6 - count: 6
Thread_5 - count: 7
Thread_4 - count: 8
Thread_3 - count: 9
Thread_2 - count: 10
counter.count: 10
 */
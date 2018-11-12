package com.company.app;

/*
NOT Thread Safe

https://www.youtube.com/watch?v=lotAYC3hLVo&list=PLBB24CFB073F1048E&index=3

count++ (read, update, assign) is not atomic. Maybe multiple threads read the same value, and then update and assign.
 */
class Counter{
    int count = 0;
    public void increment(){
        count++;
    }
}
public class App
{
    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        Thread worker1 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i++){
                    counter.increment();
                }
            }
        });
        Thread worker2 = new Thread(new Runnable() {
            public void run() {
                for(int i = 0; i < 1000; i++){
                    counter.increment();
                }
            }
        });
        worker1.start();
        worker2.start();

        try {
            worker1.join();
            worker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter.count="+counter.count);
    }
}
/*
Problem: the Counter.count is not 2000

output:
Counter.count=1703
 */

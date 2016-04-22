package com.company.app;

/*
Race condition:
1, both threads execution is interleaved
2, count is 7 instead of 10 after 10 threads called increase()

Solution:
We can use synchronized or lock to block the critical section, only one thread can access at a time.
 */
class Counter{ // shared data
    int count = 0;
    public void increase(){
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

        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    counter.increase();
                    System.out.println(Thread.currentThread().getName() + " - count: " + counter.count);
                }
            }.start();
        }

        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("counter.count: " + counter.count);
    }
}
/*
output:
Thread_3 - count: 3
Thread_2 - count: 3
Thread_1 - count: 3
Thread_4 - count: 7
Thread_9 - count: 7
Thread_8 - count: 7
Thread_6 - count: 7
Thread_10 - count: 7
Thread_7 - count: 7
Thread_5 - count: 7
counter.count: 7
 */

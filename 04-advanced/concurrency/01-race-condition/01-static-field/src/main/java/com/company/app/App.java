package com.company.app;

/*
Race condition:
1, both threads execution is interleaved
2, count is 5 instead of 10 after 10 threads called increase()

Solution:
We can use synchronized or lock to block the critical section, only one thread can access at a time.
See next 04-lock for more info.
 */
public class App 
{
    static int count = 0;
    static class Counter{ // shared data
        public void increase(){
            try {
                Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
            } catch (InterruptedException e) { }
            count++;
        }
    }
    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        for(int i = 0; i < 10; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increase();
                    System.out.println(Thread.currentThread().getName() + " - count: " + count);
                }
            }.start();
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("counter.count: " + count);
    }
}
/*
output:
Thread_A_2 - count: 2
Thread_A_3 - count: 2
Thread_A_4 - count: 2
Thread_A_5 - count: 2
Thread_A_1 - count: 2
Thread_A_8 - count: 4
Thread_A_9 - count: 5
Thread_A_7 - count: 4
Thread_A_10 - count: 5
Thread_A_6 - count: 5
counter.count: 5  <- PROBLEM: it is 5 instead of 10
 */

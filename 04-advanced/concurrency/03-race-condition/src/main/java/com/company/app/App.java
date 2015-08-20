package com.company.app;

/*

Race condition:
1, both threads execution is interleaved
2, count is 14 instead of 20 after 20 threads called increase()

Solution:
We can use synchronized or lock to block the critical section, only one thread can access at a time.
See next 04-lock for more info.

output:
Thread_B_3 - count: 9
Thread_A_1 - count: 14
Thread_B_2 - count: 9
Thread_A_10 - count: 14
Thread_B_1 - count: 14
Thread_A_9 - count: 9
Thread_A_2 - count: 9
Thread_B_4 - count: 14
Thread_A_7 - count: 9
Thread_A_5 - count: 9
Thread_A_4 - count: 9
Thread_B_10 - count: 14
Thread_A_3 - count: 14
Thread_B_7 - count: 12
Thread_B_8 - count: 13
Thread_B_9 - count: 13
Thread_A_6 - count: 9
Thread_B_6 - count: 11
Thread_A_8 - count: 9
Thread_B_5 - count: 9
counter.count: 14

 */
public class App 
{
    static int count = 1;
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
        for(int i = 1; i <= 10; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increase();
                    System.out.println(Thread.currentThread().getName() + " - count: " + count);
                }
            }.start();
        }

        for(int i = 1; i <= 10; i++){
            new Thread("Thread_B_" + i){
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

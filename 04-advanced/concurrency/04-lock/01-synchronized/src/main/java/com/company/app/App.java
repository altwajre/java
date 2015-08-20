package com.company.app;

/*
synchronized will block the threads, only one thread can access a time.

output:
Thread_A_1 - count: 1
Thread_B_10 - count: 2
Thread_B_9 - count: 3
Thread_B_8 - count: 4
Thread_B_7 - count: 5
Thread_B_6 - count: 6
Thread_B_5 - count: 7
Thread_B_4 - count: 8
Thread_B_3 - count: 9
Thread_B_2 - count: 10
Thread_B_1 - count: 11
Thread_A_10 - count: 12
Thread_A_9 - count: 13
Thread_A_8 - count: 14
Thread_A_7 - count: 15
Thread_A_6 - count: 16
Thread_A_5 - count: 17
Thread_A_4 - count: 18
Thread_A_3 - count: 19
Thread_A_2 - count: 20

 */
public class App 
{
    static int count = 0;
    static class Counter{
        public synchronized void increase(){
            try {
                Thread.sleep(100);
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
    }
}

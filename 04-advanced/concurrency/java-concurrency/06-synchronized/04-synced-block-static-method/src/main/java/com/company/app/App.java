package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html

output: one thread enter synchronized block at a time
Thread_1 - Counter.count : 0
Thread_1 - Counter.count : 1
Thread_1 - Counter.count : 2
Thread_1 - Counter.count : 3
Thread_1 - Counter.count : 4
Thread_1 - Counter.count : 5
Thread_1 - Counter.count : 6
Thread_1 - Counter.count : 7
Thread_1 - Counter.count : 8
Thread_1 - Counter.count : 9
Thread_2 - Counter.count : 10
Thread_2 - Counter.count : 11
Thread_2 - Counter.count : 12
Thread_2 - Counter.count : 13
Thread_2 - Counter.count : 14
Thread_2 - Counter.count : 15
Thread_2 - Counter.count : 16
Thread_2 - Counter.count : 17
Thread_2 - Counter.count : 18
Thread_2 - Counter.count : 19

 */
public class App
{
    static class Counter {
        public static long count = 0;
        public static void add(long value){ // synchronized critical section leads to race conditions
            String threadName = Thread.currentThread().getName();
            synchronized (Counter.class){ // synchronized block
                for(int i = 0; i < 10; i++){
                    System.out.println(threadName + " - Counter.count : " + count);
                    count += value;
                }
            }
        }
    }

    public static void main( String[] args )
    {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                Counter.add(1);
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                Counter.add(1);
            }
        }.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - counter: " + Counter.count);
    }
}

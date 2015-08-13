package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html

 output: instead of 5, the value left in this.count will be the value written by the last thread to write its value.
 Thread_1 - Counter.count before add : 0
 main - counter: 2
 Thread_2 - Counter.count before add : 2

 */
public class App
{
    static class Counter {
        protected long count = 0;
        public long getCount() {
            return count;
        }
        public void add(long value){ // critical section leads to race conditions
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " - Counter.count before add : " + count);
            this.count = this.count + value;
        }
    }

    public static void main( String[] args )
    {
        threadTest();
    }

    private static void threadTest() {
        final Counter counter = new Counter();

        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                counter.add(2);
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                counter.add(3);
            }
        }.start();

        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.getCount());
    }
}

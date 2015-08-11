package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html

 output: instead of 5, the value left in this.count will be the value written by the last thread to write its value.
 Counter.count before add : 0
 counter: 2
 Counter.count before add : 2

 */
public class App
{
    static class Counter {
        protected long count = 0;
        public long getCount() {
            return count;
        }
        public void add(long value){ // critical section loads to race conditions
            System.out.println("Counter.count before add : " + count);
            this.count = this.count + value;
        }
    }
    public static void main( String[] args )
    {
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
                counter.add(2);
            }
        }.start();

        System.out.println("counter: " + counter.getCount());
    }
}

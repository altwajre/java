package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html

 output: instead of 5, the value left in this.count will be the value written by the last thread to write its value.
 i: 0
 i: 1
 Counter.count before add : 0
 Counter.count before add : 3
 counter: 0

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
    static int i = 0;
    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        String threadName = "Thread";

        while (i < 2){
            System.out.println("i: " + i);
            threadName = threadName + "_" + i;
            Thread thread = new Thread(threadName){
                public void run(){
                    counter.add(i + 2);
                }
            };
            thread.start();
            i++;
        }

        System.out.println("counter: " + counter.getCount());

    }
}

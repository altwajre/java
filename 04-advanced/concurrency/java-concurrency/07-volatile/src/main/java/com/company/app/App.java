package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/volatile.html
One thread reads and writes the value of a volatile variable, and other threads only read the variable,
then the reading threads are guaranteed to see the latest value written to the volatile variable.

output:
Thread_read_write - Counter.count : 1
Thread_read_only - counter: 1
main - counter: 1

 */
public class App
{
    static class Counter {
        public volatile long count = 0; // volatile variable
        public void add(long value){ // critical section leads to race conditions
            String threadName = Thread.currentThread().getName();
            this.count += value;
            System.out.println(threadName + " - Counter.count : " + this.count);
        }
    }

    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        String threadName = "Thread_read_write";
        new Thread(threadName){
            public void run(){
                counter.add(1);
            }
        }.start();

        threadName = "Thread_read_only";
        new Thread(threadName){
            public void run(){
                System.out.println(Thread.currentThread().getName() + " - counter: " + counter.count);
            }
        }.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.count);
    }
}

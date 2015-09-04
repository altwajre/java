package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/synchronized.html

output: only one thread enter synchronized method at a time
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
        protected long count = 0;
        public long getCount() {
            return count;
        }
        public synchronized void add(long value){ // critical section leads to race conditions
            String threadName = Thread.currentThread().getName();
            for(int i = 0; i < 10; i++){
                System.out.println(threadName + " - Counter.count : " + this.count);
                this.count += value;
            }
        }
    }

    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                counter.add(1);
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                counter.add(1);
            }
        }.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.getCount());
    }
}

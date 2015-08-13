package com.company.app;

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
    public static void main( String[] args ) throws InterruptedException {
        final Counter counter = new Counter();

        String threadName = "Thread";
        Thread threadA = new Thread(threadName){
            public void run(){
                counter.add(2);
            }
        };
        threadA.start();

        threadName = "Thread_2";
        Thread threadB = new Thread(threadName){
            public void run(){
                counter.add(3);
            }
        };
        threadB.start();


        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.getCount());
    }
}

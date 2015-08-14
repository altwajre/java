package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/race-conditions-and-critical-sections.html

output:
20
 */
public class App
{
    static class Counter{
        long count = 0;
        public synchronized void add(long value){
            this.count += value;
        }
    }
    static class CounterThread extends Thread{
        protected Counter counter = null;
        public CounterThread(Counter counter){
            this.counter = counter;
        }
        public void run(){
            for(int i = 0; i < 10; i++){
                counter.add(1);
            }
        }
    }
    public static void main( String[] args ) throws InterruptedException {
        Counter counter = new Counter();
        Thread threadA = new CounterThread(counter);
        Thread threadB = new CounterThread(counter);
        threadA.start();
        threadB.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.count);
    }
}

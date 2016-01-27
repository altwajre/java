package com.company.app;

/*
Critical Section Throughput
For smaller critical sections making the whole critical section a synchronized block may work. But for larger critical
sections it may beneficial to break the critical section into smaller critical sections, to allow mutliple threads
to execute each a small critical section.
 */
class Counter {
    long count = 0;
    public long getCount() {
        return count;
    }
    public void add(long value){
        synchronized (this){ // synchronized to smaller critical section
            try {
                Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
            } catch (InterruptedException e) { }
            this.count = this.count + value;
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        final Counter counter = new Counter();

        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    counter.add(1);
                    System.out.println(Thread.currentThread().getName() + " - count: " + counter.count);
                }
            }.start();
        }

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.getCount());
    }
}
/*
output:
Thread_0 - count: 1
Thread_9 - count: 2
Thread_8 - count: 3
Thread_7 - count: 4
Thread_6 - count: 5
Thread_5 - count: 6
Thread_4 - count: 7
Thread_3 - count: 8
Thread_2 - count: 9
Thread_1 - count: 10
main - counter: 10
 */
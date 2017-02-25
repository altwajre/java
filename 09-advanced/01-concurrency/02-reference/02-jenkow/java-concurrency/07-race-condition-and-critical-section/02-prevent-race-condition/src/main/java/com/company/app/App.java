package com.company.app;

/*
Prevent Race Condition
To prevent race conditions from occurring you must make sure that the critical section is executed as an atomic
 instruction. That means that once a single thread is executing it, no other threads can execute it until the first
 thread has left the critical section.
Race conditions can be avoided by proper thread synchronization in critical sections. Thread synchronization can be
 achieved using synchronized block of Java code. Thread synchronization can also achieved using other synchronization
 constructs like locks or atomic variables like java.util.concurrent.atomic.AtomicInteger.
 */
class Counter {
    long count = 0;
    public long getCount() {
        return count;
    }
    public synchronized void add(long value){ // synchronized critical section
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        this.count = this.count + value;
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
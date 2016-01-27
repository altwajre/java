package com.company.app;

/*
Race Conditions in Critical Sections
The problem arise when multiple threads access the same resources. For instance the same memory (variables, arrays,
 or objects), systems (databases, web services etc) or files.
 */
class Counter {
    long count = 0;
    public long getCount() {
        return count;
    }
    public void add(long value){ // critical section leads to race conditions
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
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - counter: " + counter.getCount());
    }
}
/*
output:
Thread_3 - count: 4
Thread_2 - count: 4
Thread_1 - count: 4
Thread_4 - count: 4
Thread_5 - count: 4
Thread_6 - count: 6
Thread_9 - count: 8
Thread_10 - count: 8
Thread_8 - count: 6
Thread_7 - count: 6
main - counter: 8
 */

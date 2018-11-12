package com.company.app;

class NotThreadSafe { // NOT thread safe when share the same instance to multi threads leads to race condition
    private long count = 0; // Object Members
    public long getCount() {
        return count;
    }
    public synchronized void add(long value){ // critical section loads to race conditions
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
        objectMemberShareToThreadsTest();
    }
    private static void objectMemberShareToThreadsTest() {
        final NotThreadSafe notThreadSafe = new NotThreadSafe(); // share the same instance

        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    notThreadSafe.add(1);
                    System.out.println(Thread.currentThread().getName() + " - count: " + notThreadSafe.getCount());
                }
            }.start();
        }

        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("notThreadSafe.count: " + notThreadSafe.getCount());
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
notThreadSafe.count: 10
 */

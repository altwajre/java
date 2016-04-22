package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety.html

Race condition:
Object member variables (fields) are stored on the heap along with the object. Therefore, if two threads call a
method on the same object instance and this method updates object member variables, the method is not thread safe.
If two threads call the add() method simulaneously on the same NotThreadSafe instance then it leads to race conditions.
Notice how the two thread instances share the same NotThreadSafe instance. Therefore, when they call the add() method
on the NotThreadSafe instance it leads to race condition.
 */
class NotThreadSafe { // NOT thread safe when share the same instance to multi threads leads to race condition
    private long count = 0; // Object Members
    public long getCount() {
        return count;
    }
    public void add(long value){ // critical section loads to race conditions
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

        for(int i = 1; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    notThreadSafe.add(1);
                    System.out.println(Thread.currentThread().getName() + " - count: " + notThreadSafe.getCount());
                }
            }.start();
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("notThreadSafe.count: " + notThreadSafe.getCount());
    }
}
/*
output:
Thread_2 - count: 2
Thread_1 - count: 2
Thread_7 - count: 5
Thread_10 - count: 8
Thread_4 - count: 7
Thread_6 - count: 7
Thread_3 - count: 7
Thread_5 - count: 5
Thread_9 - count: 5
Thread_8 - count: 6
notThreadSafe.count: 8
 */
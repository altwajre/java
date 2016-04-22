package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety.html

No race condition:
If two threads call the add() method simultaneously on different instances then it does not lead to race condition.
The two threads have each their own instance of NotThreadSafe so their calls to the add method doesn't interfere with
each other. The code does not have race condition anymore. So, even if an object is not thread safe it can still be
used in a way that doesn't lead to race condition.
 */
class NotThreadSafe {
    protected long count = 0; // Object Members
    public long getCount() {
        return count;
    }
    public void add(long value){ // critical section loads to race conditions
        System.out.println(Thread.currentThread().getName() + " - Counter.count before add : " + count);
        this.count = this.count + value;
        System.out.println(Thread.currentThread().getName() + " - Counter.count after add : " + count);
    }
}
public class App
{
    public static void main( String[] args )
    {
        objectMemberShareToThreadsTest();
    }
    private static void objectMemberShareToThreadsTest() {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                new NotThreadSafe().add(2);
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                new NotThreadSafe().add(2);
            }
        }.start();
    }
}
/*
output:
Thread_1 - Counter.count before add : 0
Thread_1 - Counter.count after add : 2
Thread_2 - Counter.count before add : 0
Thread_2 - Counter.count after add : 2
 */

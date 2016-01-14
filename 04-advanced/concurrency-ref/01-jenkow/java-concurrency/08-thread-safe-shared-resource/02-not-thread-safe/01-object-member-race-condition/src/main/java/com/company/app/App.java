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
        final NotThreadSafe notThreadSafe = new NotThreadSafe(); // share the same instance
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                notThreadSafe.add(2);
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                notThreadSafe.add(2);
            }
        }.start();
        System.out.println(Thread.currentThread().getName() + " - counter: " + notThreadSafe.getCount());
    }
}
/*
output:
Thread_1 - Counter.count before add : 0
Thread_1 - Counter.count after add : 2
Thread_2 - Counter.count before add : 0
Thread_2 - Counter.count after add : 4
main - counter: 0

or

Thread_1 - Counter.count before add : 0
Thread_1 - Counter.count after add : 2
main - counter: 2
Thread_2 - Counter.count before add : 2
Thread_2 - Counter.count after add : 4
 */
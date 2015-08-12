package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety.html

    output:
    Thread_1 - Counter.count before add : 0
    Thread_1 - Counter.count after add : 2
    Thread_2 - Counter.count before add : 0
    Thread_2 - Counter.count after add : 2
 */
public class App
{
    static class ObjectMember { // NOT thread safe when share the same instance to multi threads
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
    public static void main( String[] args )
    {
        objectMemberShareToThreadsTest();
    }
    private static void objectMemberShareToThreadsTest() {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                new ObjectMember().add(2);
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                new ObjectMember().add(2);
            }
        }.start();
    }
}

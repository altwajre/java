package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety.html

 */
public class App
{
    static class ObjectMember { // Can be NOT THREAD SAFE when share the same instance to multi threads
        protected long count = 0; // Object Members
        public long getCount() {
            return count;
        }
        public void add(long value){ // critical section loads to race conditions
            System.out.println(Thread.currentThread().getName() + " Counter.count before add : " + count);
            this.count = this.count + value;
        }
    }
    static int localVariable(){ // THREAD SAFE
        int count = 0;
        count++;
        return count;
    }
    static class LocalObject{
        private String name;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public LocalObject(String name){this.name = name;}
    }
    public static void main( String[] args )
    {
        /*
        output:
        Thread_1: 1
        Thread_2: 1
         */
        localVariableShareToThreadsTest(); // thread safe

        /*
        output
        Thread_1 Counter.count before add : 0
        counter: 2
        Thread_2 Counter.count before add : 2
         */
        objectMemberShareToThreadsTest(); // NOT thread safe
    }

    private static void localVariableShareToThreadsTest() {
        System.out.println("#localVariableShareToThreadsTest - thread safe");
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                System.out.println(getName() + ": " + localVariable());
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                System.out.println(getName() + ": " + localVariable());
            }
        }.start();
    }

    private static void objectMemberShareToThreadsTest() {
        System.out.println("#objectMemberShareToThreadsTest");
        final ObjectMember counter = new ObjectMember();

        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                counter.add(2);
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                counter.add(2);
            }
        }.start();

        System.out.println("counter: " + counter.getCount());
    }
}

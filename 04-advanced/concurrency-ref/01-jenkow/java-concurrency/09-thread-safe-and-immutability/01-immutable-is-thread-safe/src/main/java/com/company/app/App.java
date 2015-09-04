package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety-and-immutability.html

 Race conditions occur only if multiple threads WRITE to the resource.
 ImmutableObject is thread safe

    output:
    Thread_1: immutableObject.add(2) is 3
    main - counter: 1
    Thread_2: immutableObject.add(2) is 3
 */
public class App 
{
    static class ImmutableObject{ // thread safe
        private int value = 0;
        public ImmutableObject(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
        /*
        If you need to perform operations like add, you can do so by returning a new instance
        with the value resulting from operation.
         */
        public ImmutableObject add(int value){
            return new ImmutableObject(this.value + value);
        }
    }
    public static void main( String[] args )
    {
        final ImmutableObject immutableObject = new ImmutableObject(1);
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                System.out.println(Thread.currentThread().getName() + ": immutableObject.add(2) is "
                        + immutableObject.add(2).getValue());
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                System.out.println(Thread.currentThread().getName() + ": immutableObject.add(2) is "
                        + immutableObject.add(2).getValue());
            }
        }.start();
        System.out.println(Thread.currentThread().getName() + " - counter: " + immutableObject.getValue());
    }
}

package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety-and-immutability.html

 Immutable object is thread safe.
 But the reference to the immutable object may not be thread safe.

    output:
    main - counter: 2

    main - counter: 3
 */
public class App
{
    static class ImmutableObject{ // thread safe
        private int value = 0;
        public ImmutableObject(int value){ this.value = value; }
        public int getValue(){ return this.value; }
        /*
        If you need to perform operations like add, you can do so by returning a new instance
        with the value resulting from operation.
         */
        public ImmutableObject add(int value){
            return new ImmutableObject(this.value + value);
        }
    }
    static class RefImmutableObject{
        private ImmutableObject immutableObject = null;
        public ImmutableObject getImmutableObject(){ return immutableObject; }
        public void setImmutableObject(ImmutableObject immutableObject){
            this.immutableObject = immutableObject;
        }
        public void add(int value){
            this.immutableObject = this.immutableObject.add(value);
        }
    }
    public static void main( String[] args )
    {
        final RefImmutableObject refImmutableObject = new RefImmutableObject();
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                refImmutableObject.setImmutableObject(new ImmutableObject(3));
            }
        }.start();
        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                refImmutableObject.setImmutableObject(new ImmutableObject(2));
            }
        }.start();
        if(refImmutableObject.getImmutableObject() != null){
            System.out.println(Thread.currentThread().getName() + " - counter: "
                    + refImmutableObject.getImmutableObject().getValue());
        }
    }
}

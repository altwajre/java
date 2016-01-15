package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety-and-immutability.html

ImmutableObject is thread safe:
Race conditions occur only if multiple threads are accessing the same resource, and one or more of the threads WRITE
to the resource. If multiple threads read the same resource race conditions do not occur.
We can make sure that objects shared between threads are never updated by any of the threads by making the shared
objects immutable, and thereby thread safe.
Notice how the value of the ImmutableObject instance is passed in the constructor. Notice also how there is no setter
method. Once an ImmutableObject instance is created you cannot change its value. It is immutable. You can read it by
using the getValue() method.
 */
class ImmutableObject{ // thread safe
    private final int value;
    public ImmutableObject(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    /*
    If you need to perform operations like add, you can do so by returning a new instance
    with the value resulting from operation.
    Notice how the add() method returns a new ImmutableValue instance with the result of the add operation, rather than
    adding the value to itself.
     */
    public ImmutableObject add(int value){
        return new ImmutableObject(this.value + value);
    }
}
public class App
{
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
/*
output:
Thread_1: immutableObject.add(2) is 3
main - counter: 1
Thread_2: immutableObject.add(2) is 3
 */

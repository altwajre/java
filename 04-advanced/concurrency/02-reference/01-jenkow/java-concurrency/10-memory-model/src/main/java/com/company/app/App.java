package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/java-memory-model.html
http://tutorials.jenkov.com/images/java-concurrency/java-memory-model-3.png
 */
class SharedObject{
    // static variable pointing to instance of SharedObject
    public static final SharedObject sharedInstance = new SharedObject();  // object3
    // member variable pointing to two objects on the heap
    public Integer object2 = new Integer(22);
    public Integer object4 = new Integer(44);
    public long member1 = 12345;
    public long member2 = 67890;
}
class MyRunnable implements Runnable{
    public void run() {
        methodOne();
    }
    public void methodOne(){
        String threadName = Thread.currentThread().getName();
        int localVariable1 = 45;
        SharedObject localVariable2 = SharedObject.sharedInstance;
        System.out.println(threadName + " - SharedObject.object2 before add: " + localVariable2.object2);
        localVariable2.object2 += 2; // add 2 on each thread
        System.out.println(threadName + " - SharedObject.object2 after add: " + localVariable2.object2);
        // do more with local variables
        methodTwo();
    }
    public void methodTwo(){
        Integer localVariable1 = new Integer(99); // object1 and object5
        // do more with local variable
    }
}
public class App
{
    public static void main( String[] args )
    {
        SharedObject so1 = SharedObject.sharedInstance;
        SharedObject so2 = SharedObject.sharedInstance;
        if(so1 == so2) System.out.println("same instance");
        else System.out.println("different instances");

        new Thread(new MyRunnable(), "thread_1").start();
        new Thread(new MyRunnable(), "thread_2").start();
        System.out.println("SharedObject.sharedInstance: " + SharedObject.sharedInstance.object2);
    }
}
/*
output:
thread_1 - SharedObject.object2 before add: 22
thread_2 - SharedObject.object2 before add: 22
thread_2 - SharedObject.object2 after add: 26
SharedObject.sharedInstance: 22
thread_1 - SharedObject.object2 after add: 24

SharedObject.sharedInstance: 22
thread_2 - SharedObject.object2 before add: 22
thread_1 - SharedObject.object2 before add: 22
thread_2 - SharedObject.object2 after add: 24
thread_1 - SharedObject.object2 after add: 26
 */
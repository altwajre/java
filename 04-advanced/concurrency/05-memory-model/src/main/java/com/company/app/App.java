package com.company.app;

public class App
{
    static class SharedObject{
        // static variable pointing to instance of SharedObject
        public static final SharedObject sharedInstance = new SharedObject();  // object3
        // member variable pointing to two objects on the heap
        public Integer object2 = new Integer(22);
        public Integer object4 = new Integer(44);
        public long member1 = 12345;
        public long member2 = 67890;
    }
    static class MyRunnable implements Runnable{
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println("MyRunnable.run on thread " + threadName);
            methodOne();
        }
        public void methodOne(){
            String threadName = Thread.currentThread().getName();
            System.out.println("methodOne on thread " + threadName);
            int localVariable1 = 45;
            SharedObject localVariable2 = SharedObject.sharedInstance;
            // do more with local variables
            methodTwo();
        }
        public void methodTwo(){
            Integer localVariable1 = new Integer(99); // object1 and object5
            // do more with local variable
        }
    }
    public static void main( String[] args )
    {
        SharedObject so1 = SharedObject.sharedInstance;
        SharedObject so2 = SharedObject.sharedInstance;
        if(so1 == so2) System.out.println("same instance");
        else System.out.println("different instances");

        new Thread(new MyRunnable(), "thread_1").start();
        new Thread(new MyRunnable(), "thread_2").start();
    }
}

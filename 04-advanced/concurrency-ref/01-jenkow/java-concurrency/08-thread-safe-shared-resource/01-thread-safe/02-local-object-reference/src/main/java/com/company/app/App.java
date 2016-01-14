package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety.html
Local references to objects are a bit different. The reference itself is not shared. The object referenced
is not stored in each thread's local stack. All objects are stored in the shared heap.
If an object created locally never escapes the method it was created in, it is thread safe.
 */
class LocalObject{
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalObject(String name){this.name = name;}
}
public class App
{
    static void localObjectReference(){
        LocalObject localObject = new LocalObject("Tom"); // local reference object is thread safe
        System.out.println(Thread.currentThread().getName() + ": " + localObject.getName());
        otherMethod(localObject);
        System.out.println(Thread.currentThread().getName() + ": " + localObject.getName());
    }
    static void otherMethod(LocalObject localObject){
        localObject.setName("Other name");
    }
    public static void main( String[] args )
    {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                localObjectReference();
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                localObjectReference();
            }
        }.start();
    }
}
/*
output:
Thread_1: Tom
Thread_2: Tom
Thread_2: Other name
Thread_1: Other name
 */
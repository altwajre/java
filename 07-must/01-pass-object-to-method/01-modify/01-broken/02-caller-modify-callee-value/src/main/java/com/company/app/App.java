package com.company.app;

/*
Race condition when multiple threads call a method has object parameter.
http://www.dreamincode.net/forums/topic/236550-passing-objects-to-a-method/
 */
class Node{
    public int Data;
}
public class App
{
    public static void main( String[] args )
    {
        final Node node = new Node();

        new Thread("Thread_1"){
            public void run(){
                node.Data = 1;
                passObject(node);
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                try {
                    Thread.sleep(100);  // add sleep(1000) to make it easy to reproduce the race condition
                } catch (InterruptedException e) { }
                node.Data = 2;
                passObject(node);
            }
        }.start();
    }

    /*
    We pass a handle of an object, and in the callee method, a new handle created and pointed to the same object.
    When more than one handles tied to the same object, it is known as aliasing.
    For example, both "caller" and "callee" point to the same object "[1]".
caller  callee
     \ /
     [1]
    BROKEN: When using "caller" to change data ".Data=2", it will affect "callee" which means "callee.Data" is "2".
caller  callee
     \ /
     [2]
     */
    static void passObject(Node callee){
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " BEFORE sleep: " + callee.Data);
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        System.out.println(threadName + " AFTER sleep: " + callee.Data);
    }
}
/*
output:
Thread_1 BEFORE sleep: 1
Thread_2 BEFORE sleep: 2
Thread_1 AFTER sleep: 2
Thread_2 AFTER sleep: 2
 */

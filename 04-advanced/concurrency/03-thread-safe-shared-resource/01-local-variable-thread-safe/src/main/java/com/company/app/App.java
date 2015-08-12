package com.company.app;

/*
 http://tutorials.jenkov.com/java-concurrency/thread-safety.html

    output:
    Thread_1: 1
    Thread_2: 1
 */
public class App
{
    static int foo(){ // THREAD SAFE
        int count = 0;
        count++;
        return count;
    }
    public static void main( String[] args )
    {
        localVariableShareToThreadsTest(); // thread safe
    }
    private static void localVariableShareToThreadsTest() {
        String threadName = "Thread_1";
        new Thread(threadName){
            public void run(){
                System.out.println(getName() + ": " + foo());
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                System.out.println(getName() + ": " + foo());
            }
        }.start();
    }
}

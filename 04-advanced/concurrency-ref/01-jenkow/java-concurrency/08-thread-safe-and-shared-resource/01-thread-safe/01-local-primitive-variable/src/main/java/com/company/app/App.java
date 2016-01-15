package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-safety.html
Local variables are stored in each thread's own stack. That means that local variables are never shared between
threads. That also means that all local primitive variables are thread safe.
 */
public class App
{
    static int localPrimitiveVariable(){
        int count = 0; // local primitive variable is thread safe
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
                System.out.println(getName() + ": " + localPrimitiveVariable());
            }
        }.start();

        threadName = "Thread_2";
        new Thread(threadName){
            public void run(){
                System.out.println(getName() + ": " + localPrimitiveVariable());
            }
        }.start();
    }
}
/*
output:
Thread_1: 1
Thread_2: 1
 */
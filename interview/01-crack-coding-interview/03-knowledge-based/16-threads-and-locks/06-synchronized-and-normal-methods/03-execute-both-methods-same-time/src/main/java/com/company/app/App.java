package com.company.app;

/*

Question:
You are given a class with synchronized method_A and a normal method_B. If you have two threads in one instance of a
program, can they execute method_A and method_B at the same time?

Solution:
Yes, they can execute method_A and method_B at the same time. Since method_B is not synchronized, there is nothing to
block thread1 from executing method_A while thread2 is executing method_B. This is true regardless of whether thread1
and thread2 have the same instance of the object or not.

output:
Thread_1: starting methodA()
Thread_2: starting methodB()
threads started
Thread_2: ending methodB()
Thread_1: ending methodA()
done

 */
public class App
{
    static class Foo{
        public void pause(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public synchronized void methodA(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": starting methodA()");
            pause();
            System.out.println(threadName + ": ending methodA()");
        }
        public void methodB(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": starting methodB()");
            pause();
            System.out.println(threadName + ": ending methodB()");
        }
    }
    public static void main( String[] args )
    {
        final Foo foo = new Foo();
        Thread thread1 = new Thread("Thread_1"){
            public void run(){
                foo.methodA();
            }
        };
        thread1.start();
        Thread thread2 = new Thread("Thread_2"){
            public void run(){
                foo.methodB();
            }
        };
        thread2.start();
        System.out.println("threads started");
        while(thread1.isAlive() || thread2.isAlive()){}
        System.out.println("done");
    }
}

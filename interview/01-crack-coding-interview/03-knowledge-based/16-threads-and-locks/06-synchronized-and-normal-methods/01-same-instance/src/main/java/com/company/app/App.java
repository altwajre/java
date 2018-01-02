package com.company.app;

/*

Question:
You are given a class with synchronized method_A and a normal method_B. If you have two threads in one instance of a
program, can they both execute method_A at the same time?

Solution:
No, they can not both execute method_A at the same time.

output:
Thread_1: starting methodA()
threads started
Thread_1: ending methodA()
Thread_2: starting methodA()
Thread_2: ending methodA()
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
                foo.methodA();
            }
        };
        thread2.start();
        System.out.println("threads started");
        while(thread1.isAlive() || thread2.isAlive()){}
        System.out.println("done");
    }
}

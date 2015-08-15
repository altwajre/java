package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/starvation-and-fairness.html

Problem: synchronized blocks makes no guarantees about what thread is being granted access if
         more than one thread is waiting to enter.

output:
 Thread_1 finish
 Thread_5 finish
 Thread_4 finish
 Thread_3 finish
 Thread_2 finish

 */
public class App
{
    static long duration = 1000;
    static class Synchronizer{
        public synchronized void doSynchronized(){
            // do a lot of work which takes a long time
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main( String[] args ) {
        final Synchronizer synchronizer = new Synchronizer();

        for(int i = 1; i <= 5; i++){
            String threadName = "Thread_" + i;
            new Thread(threadName){
                public void run(){
                    synchronizer.doSynchronized();
                    String threadName = Thread.currentThread().getName();
                    System.out.println(" " + threadName + " finish");
                }
            }.start();
        }
    }
}

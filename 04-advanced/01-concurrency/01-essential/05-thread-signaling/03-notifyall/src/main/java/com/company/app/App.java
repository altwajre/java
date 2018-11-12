package com.company.app;

import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

notifyAll() will wake all threads waiting on a given object.

output:
Wait_Thread_1: wait
Wait_Thread_3: wait
Wait_Thread_2: wait
NotifyAll_Thread: notifyAll
Wait_Thread_2: finished
Wait_Thread_3: finished
Wait_Thread_1: finished

 */
public class App 
{
    static class Signal{ // wait and notifyAll
        public synchronized void doWait(){
            try {
                System.out.println(Thread.currentThread().getName() + ": wait");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": finished");
        }
        public synchronized void doNotifyAll(){
            System.out.println(Thread.currentThread().getName() + ": notifyAll");
            notifyAll();
        }
    }
    public static void main( String[] args ) throws InterruptedException {
        final Signal signal = new Signal();

        // multi wait threads
        for(int i = 1; i <= 3; i++){
            new Thread("Wait_Thread_" + i){
                public void run(){
                    signal.doWait();
                }
            }.start();
        }

        TimeUnit.SECONDS.sleep(1);

        // one notifyAll thread wakes all waiting threads
        new Thread("NotifyAll_Thread"){
            public void run(){
                signal.doNotifyAll();
            }
        }.start();
    }
}

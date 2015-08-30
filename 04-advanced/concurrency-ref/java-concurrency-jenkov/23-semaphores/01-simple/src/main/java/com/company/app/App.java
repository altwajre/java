package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/semaphores.html

Two threads signaling each other using a Semaphore

For example, like Stop-Light lets a car pass it one at a time
1, The Car thread that has many cars waiting on the Stop-Light thread
2, For the Stop-Light thread, each green signal lets a car that is in front of the Stop-Light pass and then becomes RED

output:
Thread_Car__1: wait

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__1: start going
Thread_Car__1: pass Stop-Light
Thread_Car__2: wait

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__2: start going
Thread_Car__2: pass Stop-Light
Thread_Car__3: wait

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__3: start going
Thread_Car__3: pass Stop-Light
Thread_Car__4: wait

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__4: start going
Thread_Car__4: pass Stop-Light

 */
public class App 
{
    static class Semaphore{
        boolean signal = false;
        public synchronized void take(){
            this.signal = true;
            this.notify();
        }
        public synchronized void release(){
            while(!this.signal){
                try {
                    wait();
                } catch (InterruptedException e) { }
            }
            this.signal = false;
        }
    }
    static int count = 1;
    public static void main( String[] args )
    {
        final Semaphore semaphore = new Semaphore();
        Thread carThread = new Thread("Thread_Car_"){
            public void run(){
                while (true){
                    String threadName = Thread.currentThread().getName() + "_" + count++;
                    System.out.println(threadName + ": wait in front of Stop-Light");
                    semaphore.release();  // wait
                    try {
                        System.out.println(threadName + ": start going");
                        Thread.sleep(10);  // do something
                        System.out.println(threadName + ": pass Stop-Light");
                    } catch (InterruptedException e) { }
                }
            }
        };
        carThread.start();
        Thread stopLightThread = new Thread("Thread_StopLight"){
            public void run(){
                while(true){  // do something, then signal
                    try {
                        System.out.println("\n" + Thread.currentThread().getName() + ": RED; ticking");
                        Thread.sleep(1000);  // do something
                    } catch (InterruptedException e) { }
                    System.out.println(Thread.currentThread().getName() + ": GREEN; notify");
                    semaphore.take();  // notify
                    try { Thread.sleep(15);  // do something
                    } catch (InterruptedException e) { }
                }
            }
        };
        stopLightThread.start();
    }
}

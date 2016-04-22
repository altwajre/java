package com.company.app;

import java.util.concurrent.Semaphore;

/*
Compare it with 01-jenkow, java-concurrency, 23-semaphores, 01-mutex, http://tutorials.jenkov.com/java-concurrency/semaphores.html

Semaphore.acquire(); // wait - Acquires a permit from this semaphore, blocking until one is available
Semaphore.release(); // notify - Releases a permit

Two threads signaling each other using a Semaphore

Mutex (Just 1 thread):
Mutexes are typically used to serialise access to a section of re-entrant code that cannot be executed concurrently by
more than one thread. A mutex object only allows one thread into a controlled section, forcing other threads which
attempt to gain access to that section to wait until the first thread has exited from that section.

For example, like Stop-Light lets a car pass it one at a time
1, The Car thread that has many cars waiting on the Stop-Light thread
2, For the Stop-Light thread, each green signal lets a car that is in front of the Stop-Light pass and then becomes RED
 */
public class App
{
    static int count = 1;
    public static void main( String[] args )
    {
        final Semaphore mutex = new Semaphore(1);
        Thread carThread = new Thread("Thread_Car_"){
            public void run(){
                while(true){
                    String threadName = Thread.currentThread().getName() + "_" + count++;
                    System.out.println(threadName + ": wait in front of Stop-Light");
                    try {
                        mutex.acquire(); // wait - Acquires a permit from this semaphore, blocking until one is available
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
                while (true) { // do something, then signal
                    try {
                        System.out.println("\n" + Thread.currentThread().getName() + ": RED; ticking");
                        Thread.sleep(1000);  // do something
                    } catch (InterruptedException e) { }
                    System.out.println(Thread.currentThread().getName() + ": GREEN; notify");
                    mutex.release(); // notify - Releases a permit
                    try { Thread.sleep(15);  // do something
                    } catch (InterruptedException e) { }
                }
            }
        };
        stopLightThread.start();
    }
}
/*
output:
Thread_Car__1: wait in front of Stop-Light

Thread_StopLight: RED; ticking
Thread_Car__1: start going
Thread_Car__1: pass Stop-Light
Thread_Car__2: wait in front of Stop-Light
Thread_StopLight: GREEN; notify
Thread_Car__2: start going
Thread_Car__2: pass Stop-Light
Thread_Car__3: wait in front of Stop-Light

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__3: start going
Thread_Car__3: pass Stop-Light
Thread_Car__4: wait in front of Stop-Light

Thread_StopLight: RED; ticking
Thread_StopLight: GREEN; notify
Thread_Car__4: start going
Thread_Car__4: pass Stop-Light
Thread_Car__5: wait in front of Stop-Light
 */
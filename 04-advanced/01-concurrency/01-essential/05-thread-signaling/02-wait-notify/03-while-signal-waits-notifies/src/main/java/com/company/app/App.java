package com.company.app;

import java.util.HashSet;
import java.util.Set;

/*
while loop is a spin lock

Problem: Spurious Wakeups
1, it is possible for threads to wake up without notify()

Solution:
1, use while loop instead of if-statement to guard against spurious wakeup.

output:
wait
notify
wait finished

output:
first_round_wait_thread_1 wait
first_round_wait_thread_3 wait
first_round_wait_thread_2 wait
  first_round_notify_thread_1 notify
    first_round_wait_thread_1 finished
  first_round_notify_thread_2 notify
    first_round_wait_thread_3 finished
second_round_wait_thread_1 wait
second_round_wait_thread_2 wait
second_round_wait_thread_3 wait
  second_round_notify_thread_2 notify
  second_round_notify_thread_1 notify
    first_round_wait_thread_2 finished
    second_round_wait_thread_1 finished
#remaining waiting threads: [second_round_wait_thread_2, second_round_wait_thread_3]

 */
public class App 
{
    static class MonitorObject {
        boolean isSignalled = false;
        public void doWait() throws InterruptedException {
            synchronized (this){
                while(!isSignalled){ // NOTE: spin lock
                    wait();
                }
                isSignalled = false;
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();
                isSignalled = true;
            }
        }
    }
    static MonitorObject monitorObject = new MonitorObject();
    static Set<String> set = new HashSet<String>();
    public static void main( String[] args ) throws InterruptedException {
        String threadName = "first_round_wait_thread_";
        long waitTime = 1;
        waitThreads(threadName, waitTime);
        threadName = "first_round_notify_thread_";
        waitTime = 2;
        notifyThreads(threadName, waitTime);

        threadName = "second_round_wait_thread_";
        waitTime = 3;
        waitThreads(threadName, waitTime);
        threadName = "second_round_notify_thread_";
        waitTime = 4;
        notifyThreads(threadName, waitTime);

        Thread.sleep(100);
        System.out.println("#remaining waiting threads: " + set);
    }
    private static void waitThreads(String threadName, final long duration) {
        for(int i = 1; i <= 3; i++){
            Thread thread = new Thread(threadName + i){
                public void run(){
                    try {
                        Thread.sleep(duration);
                        set.add(Thread.currentThread().getName());
                        System.out.println(Thread.currentThread().getName() + " wait");
                        monitorObject.doWait();
                    } catch (Exception e) {}
                    set.remove(Thread.currentThread().getName());
                    System.out.println("    " + Thread.currentThread().getName() + " finished");
                }
            };
            thread.setDaemon(true); // the worker thread terminates when the main thread terminates
            thread.start();
        }
    }
    private static void notifyThreads(String threadName, final long duration) {
        for(int i = 1; i <= 2; i++){
            new Thread(threadName + i){
                public void run(){
                    try { Thread.sleep(duration); } catch (Exception e) {}
                    System.out.println("  " + Thread.currentThread().getName() + " notify");
                    monitorObject.doNotify();
                }
            }.start();
        }
    }
}

package com.company.app;

import java.util.HashSet;
import java.util.Set;

/*
Multiple wait threads and notify threads by using one MonitorObject

output:
first_round_wait_thread_1 wait
first_round_wait_thread_2 wait
first_round_wait_thread_3 wait
  first_round_notify_thread_1 notify
  first_round_notify_thread_2 notify
    first_round_wait_thread_1 finished
    first_round_wait_thread_2 finished
second_round_wait_thread_2 wait
second_round_wait_thread_1 wait
second_round_wait_thread_3 wait
  second_round_notify_thread_1 notify
  second_round_notify_thread_2 notify
    first_round_wait_thread_3 finished
    second_round_wait_thread_2 finished
#remaining waiting threads: [second_round_wait_thread_3, second_round_wait_thread_1]

 */
public class App
{
    static class MonitorObject {
        public void doWait() throws InterruptedException {
            synchronized (this){
                wait();  // exception occurs when not using synchronized
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();  // exception occurs when not using synchronized
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

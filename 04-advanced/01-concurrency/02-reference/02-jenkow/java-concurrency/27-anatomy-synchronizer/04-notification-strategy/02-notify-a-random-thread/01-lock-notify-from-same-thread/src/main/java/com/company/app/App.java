package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/anatomy-of-a-synchronizer.html#notificationstrategy

Notification Strategy
Notify 1 random of N waiting threads
The notifying thread call notify() on the object the waiting threads have called wait() on. Calling notify
makes no guarantee about which of the waiting threads will be notified.

output:
  pool-1-thread-1; exit lock
  pool-1-thread-3; wait
  pool-1-thread-2; wait
  pool-1-thread-4; wait
attempt to shutdown executor
  pool-1-thread-5; wait
  pool-1-thread-1; exit unlock
  pool-1-thread-3; EXIT wait
  pool-1-thread-3; exit lock
pool-1-thread-1 finished; count=1
  pool-1-thread-3; exit unlock
pool-1-thread-3 finished; count=2
  pool-1-thread-2; EXIT wait
  pool-1-thread-2; exit lock
  pool-1-thread-2; exit unlock
pool-1-thread-2 finished; count=3
  pool-1-thread-4; EXIT wait
  pool-1-thread-4; exit lock
  pool-1-thread-4; exit unlock
pool-1-thread-4 finished; count=4
  pool-1-thread-5; EXIT wait
  pool-1-thread-5; exit lock
  pool-1-thread-5; exit unlock
pool-1-thread-5 finished; count=5

 */
public class App
{
    static class Counter {
        public int count = 0;
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;  // critical section
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finished; count=" + count);
        }
    }
    static class Lock{
        private Object monitorObject = new Object();
        private boolean isLocked = false;
        public void lock(){
            synchronized (monitorObject){
                while(isLocked){
                    System.out.println("  "+Thread.currentThread().getName() + "; wait");
                    try {
                        monitorObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("  "+Thread.currentThread().getName() + "; EXIT wait");
                }
                isLocked = true;
                System.out.println("  "+Thread.currentThread().getName() + "; exit lock");
            }
        }
        public void unlock(){
            synchronized (monitorObject){
                isLocked = false;
                monitorObject.notify();
                System.out.println("  "+Thread.currentThread().getName() + "; exit unlock");
            }
        }
    }
    public static void main( String[] args )
    {
        Counter counter = new Counter();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable task = () -> {
            counter.increment();
        };
        for(int i = 1; i <= 5; i++){
            executor.submit(task);
        }

        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
        }
        catch (InterruptedException e){
            System.err.println("tasks interrupted");
        }
        finally {
            if(!executor.isTerminated()){
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}

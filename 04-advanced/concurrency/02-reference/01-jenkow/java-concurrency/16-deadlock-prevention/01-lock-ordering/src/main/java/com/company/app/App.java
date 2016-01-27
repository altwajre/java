package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-concurrency/deadlock-prevention.html

Thread 1:
  lock A
  lock B
Thread 2:
  wait for A
  lock C (when A locked)
Thread 3:
  wait for A
  wait for B
  wait for C

output:
  pool-1-thread-1 lock Lock_A
  pool-1-thread-1 lock lock_B
attempt to shutdown executor
  pool-1-thread-2 Lock_A.wait()
  pool-1-thread-3 Lock_A.wait()
pool-1-thread-1 lock_B.notify()
pool-1-thread-1 Lock_A.notify()
pool-1-thread-1 finished
  pool-1-thread-2 lock Lock_A
pool-1-thread-2 Lock_A.notify()
  pool-1-thread-2 lock lock_C
pool-1-thread-2 lock_C.notify()
  pool-1-thread-3 lock Lock_A
pool-1-thread-3 Lock_A.notify()
  pool-1-thread-3 lock lock_B
pool-1-thread-3 lock_B.notify()
  pool-1-thread-3 lock lock_C
pool-1-thread-3 lock_C.notify()
pool-1-thread-3 finished
pool-1-thread-2 finished

 */
public class App 
{
    static class Lock{
        public Lock(String name){ this.name = name; }
        public String name;
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println("  "+Thread.currentThread().getName() + " " + name + ".wait()");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("  "+Thread.currentThread().getName() + " lock " + name);
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " " + name + ".notify()");
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args )
    {
        Lock lock_A = new Lock("Lock_A");
        Lock lock_B = new Lock("lock_B");
        Lock lock_C = new Lock("lock_C");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            lock_A.lock(); // lock A
            lock_B.lock(); // lock B
            sleep(200);
            lock_B.unlock();
            lock_A.unlock();
            System.out.println(threadName + " finished");
        });
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            sleep(100);
            lock_A.lock(); // wait for A
            lock_A.unlock();
            lock_C.lock(); // lock C
            lock_C.unlock();
            System.out.println(threadName + " finished");
        });
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            sleep(100);
            lock_A.lock(); // wait for A
            lock_A.unlock();
            lock_B.lock(); // wait for B
            lock_B.unlock();
            lock_C.lock(); // wait for C
            lock_C.unlock();
            System.out.println(threadName + " finished");
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
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
    static void sleep(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

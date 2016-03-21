package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*

output:
  pool-1-thread-1 lock lock_A
attempt to shutdown executor
  pool-1-thread-2 lock Lock_B
  pool-1-thread-1 Lock_B.wait()
  pool-1-thread-2 lock_A.wait()
shutdown finished
  pool-1-thread-2 lock_A.wait()
  pool-1-thread-1 Lock_B.wait()
cancel non-finished tasks
java.lang.InterruptedException

 */
public class App 
{
    static class Lock{
        public Lock(String name){
            this.name = name;
        }
        public String name;
        public boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while (isLocked){
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
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Lock lock_A = new Lock("lock_A");
        Lock lock_B = new Lock("Lock_B");

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            lock_A.lock();
            sleep(200);
            lock_B.lock();

            lock_B.unlock();
            lock_A.unlock();
            System.out.println(threadName + " finished");
        });
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            lock_B.lock();
            sleep(200);
            lock_A.lock();

            lock_A.unlock();
            lock_B.unlock();
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

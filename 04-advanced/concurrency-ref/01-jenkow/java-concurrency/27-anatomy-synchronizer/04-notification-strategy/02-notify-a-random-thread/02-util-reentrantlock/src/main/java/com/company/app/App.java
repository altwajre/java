package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/*

lock critical section

output:
attempt to shutdown executor
pool-1-thread-1 finished; count=1
pool-1-thread-2 finished; count=2
pool-1-thread-3 finished; count=3
pool-1-thread-4 finished; count=4
pool-1-thread-5 finished; count=5
shutdown finished

 */
public class App 
{
    static class Counter {
        public int count = 0;
        ReentrantLock lock = new ReentrantLock(false);
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++; // critical section
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " finished; count=" + count);
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

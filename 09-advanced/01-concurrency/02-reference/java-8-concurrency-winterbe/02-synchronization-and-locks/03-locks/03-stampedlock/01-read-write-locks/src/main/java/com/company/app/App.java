package com.company.app;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

StampedLock also supports read and write locks. In contrast to ReadWriteLock the locking methods of a StampedLock
returns a stamp represented by a long value. You can use these stamps to either release a lock or to check if the
lock is still valid. Additionally stamped locks support optimistic locking.

output:
stamp=384
pool-1-thread-1 start writing
attempt to shutdown executor
pool-1-thread-1 finished writing
  pool-1-thread-2 start reading
  pool-1-thread-3 start reading
Tom
  pool-1-thread-3 finished reading
Tom
  pool-1-thread-2 finished reading
shutdown finished

 */
public class App 
{
    public static void main( String[] args )
    {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Map<Integer, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
           long stamp = lock.writeLock();
            System.out.println("stamp="+stamp);
            try{
                System.out.println(Thread.currentThread().getName() + " start writing");
                sleep(2);
                map.put(1, "Tom");
                System.out.println(Thread.currentThread().getName() + " finished writing");
            }
            finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try{
                System.out.println("  "+Thread.currentThread().getName() + " start reading");
                sleep(1);
                System.out.println(map.get(1));
                System.out.println("  "+Thread.currentThread().getName() + " finished reading");
            }
            finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

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
    static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

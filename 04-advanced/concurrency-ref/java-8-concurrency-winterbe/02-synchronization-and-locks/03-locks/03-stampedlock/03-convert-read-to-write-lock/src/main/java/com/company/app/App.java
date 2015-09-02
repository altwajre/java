package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/*
http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

Sometimes it's useful to convert a read lock into a write lock without unlocking and locking again.

For example below:
The task first obtains a read lock and prints the current value of field count to the console. But if the current
value is zero we change count to 18. We first have to convert the read lock into a write lock to not break
potential concurrent access by other threads. Calling tryConvertToWriteLock() doesn't block but may return a zero
stamp indicating that no write lock is currently available. In that case we call writeLock() to block the current
thread until a write lock is available.

output:
attempt to shutdown executor
readLock stamp=257
tryConvertToWriteLock stamp=384
18
unlock stamp=384
shutdown finished

 */
public class App {
    private static int count = 0;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        StampedLock lock = new StampedLock();
        executor.submit(() -> {
            long stamp = lock.readLock();
            System.out.println("readLock stamp=" + stamp);
            try {
                if(count == 0){
                    stamp = lock.tryConvertToWriteLock(stamp);
                    System.out.println("tryConvertToWriteLock stamp=" + stamp);
                    if(stamp == 0L){
                        System.out.println("Could not convert to write lock");
                        stamp = lock.writeLock();
                    }
                    count = 18;
                }
                System.out.println(count);
            } finally {
                System.out.println("unlock stamp=" + stamp);
                lock.unlock(stamp);
            }
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(8, TimeUnit.SECONDS);
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

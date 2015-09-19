package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*

Question:
Suppose we have the following code:
public class Foo {
  public Foo(){...}
  public void first() {...}
  public void second() {...}
  public void third() {...}
}

The same instance of Foo will be passed to three different threads. ThreadA will call first, threadB will call second,
and threadC will call third. Design a mechanism to ensure that first is called before second can second is called before third.

The general logic is to check if first() has completed before executing second(), and if second() has completed before
calling third(). Because we need to be very careful about thread safety, simple boolean flags won’t do the job.

Lock has lockingThread property won't work due to the concept of lock ownership. One thread is actually performing the
lock (in the FooBad constructor), but different threads attempt to unlock the locks. This is not allowed, and your code
will raise an exception. A lock in Java is owned by the same thread which locked it.

Solution:
Use Semaphore to allow two threads signaling eachother

output:
Started Executing 1
attempt to shutdown executor
Finished Executing 1
Started Executing 2
Finished Executing 2
Started Executing 3
Finished Executing 3
shutdown finished

 */
public class App 
{
    static class Foo{
        public int pauseTime = 1000;
        public Semaphore semaphore1;
        public Semaphore semaphore2;
        public Foo(){
            try {
                semaphore1 = new Semaphore(1);
                semaphore2 = new Semaphore(1);
                semaphore1.acquire();
                semaphore2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void first(){
            try {
                System.out.println("Started Executing 1");
                Thread.sleep(pauseTime);
                System.out.println("Finished Executing 1");
                semaphore1.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void second(){
            try{
                semaphore1.acquire();
                semaphore1.release();
                System.out.println("Started Executing 2");
                Thread.sleep(pauseTime);
                System.out.println("Finished Executing 2");
                semaphore2.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void third(){
            try{
                semaphore2.acquire();
                semaphore2.release();
                System.out.println("Started Executing 3");
                Thread.sleep(pauseTime);
                System.out.println("Finished Executing 3");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void main( String[] args )
    {
        Foo foo = new Foo();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() ->{
            foo.first();
        });
        executor.submit(() -> {
            foo.second();
        });
        executor.submit(() -> {
            foo.third();
        });
        stop(executor);
    }
    static void stop(ExecutorService executor){
        try{
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(4, TimeUnit.SECONDS);
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

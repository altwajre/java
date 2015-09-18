package com.company.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
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

output:
Started Executing 1
attempt to shutdown executor
Finished Executing 1
java.lang.IllegalMonitorStateException: Calling thread has not locked this lock
	at com.company.app.App$Lock.unlock(App.java:55)
	at com.company.app.App$FooBad.first(App.java:77)
	at com.company.app.App.lambda$testThreadPool$0(App.java:120)
	at com.company.app.App$$Lambda$1/495053715.run(Unknown Source)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)

 */
public class App 
{
    static class Lock{
        private boolean isLocked = false;
        Thread lockingThread;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLocked = true;
                lockingThread = Thread.currentThread();
            }
        }
        public void unlock(){
            synchronized (this){
                if(lockingThread != Thread.currentThread()){
                    throw new IllegalMonitorStateException("Calling thread has not locked this lock");
                }
                notify();
                isLocked = false;
            }
        }
    }
    static class FooBad {
        public int pauseTime = 1000;
        public Lock lock1;
        public Lock lock2;
        public FooBad(){
            lock1 = new Lock();
            lock2 = new Lock();
            lock1.lock();
            lock2.lock();
        }
        public void first(){
            try {
                System.out.println("Started Executing 1");
                Thread.sleep(pauseTime);
                System.out.println("Finished Executing 1");
                lock1.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void second(){
            try {
                lock1.lock();
                lock1.unlock();
                System.out.println("Started Executing 2");
                Thread.sleep(pauseTime);
                System.out.println("Finished Executing 2");
                lock2.unlock();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void third(){
            try{
                lock2.lock();
                lock2.unlock();
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
        FooBad foo = new FooBad();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
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

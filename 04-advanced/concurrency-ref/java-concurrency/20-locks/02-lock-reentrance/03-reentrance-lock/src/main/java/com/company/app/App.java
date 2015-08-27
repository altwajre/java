package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/locks.html

Problem:
A thread calling outer() will first lock the Lock instance. Then it will call inner(). Inside the inner() method the
thread will again try to lock the Lock instance. This will fail (meaning the thread will be blocked), since the Lock
instance was locked already in the outer() method.

The reason the thread will be blocked the second time it calls lock() without calling unlock() in between, is apparent
when we look at the lock() implementation.

It is the condition inside the while loop (spin lock) that determines if a thread is allowed to exit the lock() method or
not. Currently the condition is that isLocked must be false for this to be allowed, regardless of what thread locked it.

output:
Thread_1 isLocked=false; outer
Thread_1 currentLockedThread = null
Thread_1 (currentLockedThread != currentThread) = true
Thread_1 (isLocked && currentLockedThread != currentThread) = false
Thread_3 isLocked=true; outer
Thread_3 currentLockedThread = Thread_1
Thread_3 (currentLockedThread != currentThread) = true
Thread_3 (isLocked && currentLockedThread != currentThread) = true
  Thread_3 wait outer
Thread_2 isLocked=true; outer
Thread_2 currentLockedThread = Thread_1
Thread_2 (currentLockedThread != currentThread) = true
Thread_2 (isLocked && currentLockedThread != currentThread) = true
  Thread_2 wait outer
Thread_1 outer()
Thread_1 isLocked=true; inner
Thread_1 currentLockedThread = Thread_1
Thread_1 (currentLockedThread != currentThread) = false
Thread_1 (isLocked && currentLockedThread != currentThread) = false
Thread_1 inner()
  Thread_1 notify outer
Thread_3 outer()
Thread_3 isLocked=true; inner
Thread_3 currentLockedThread = Thread_3
Thread_3 (currentLockedThread != currentThread) = false
Thread_3 (isLocked && currentLockedThread != currentThread) = false
Thread_3 inner()
  Thread_3 notify outer
Thread_2 outer()
Thread_2 isLocked=true; inner
Thread_2 currentLockedThread = Thread_2
Thread_2 (currentLockedThread != currentThread) = false
Thread_2 (isLocked && currentLockedThread != currentThread) = false
Thread_2 inner()
  Thread_2 notify outer

 */
public class App
{
    static class Lock{
        boolean isLocked = false;
        Thread currentLockedThread = null;
        int lockedCount = 0;
        public synchronized void lock(String msg){
            Thread currentThread = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + " isLocked=" + isLocked + "; " + msg);
            System.out.println(Thread.currentThread().getName() + " currentLockedThread = " + ((currentLockedThread == null) ? "null" : currentLockedThread.getName()));
            System.out.println(Thread.currentThread().getName() + " (currentLockedThread != currentThread) = " + (currentLockedThread != currentThread));
            System.out.println(Thread.currentThread().getName() + " (isLocked && currentLockedThread != currentThread) = " + (isLocked && currentLockedThread != currentThread));
            /*
            add "&& currentLockedThread != currentThread" to while loop to prevent inner to try to lock
            the locked Lock instance again. Because if currentThread is already a lockedThread, there is
            no need to try to lock it again.
             */
            //
            while(isLocked && currentLockedThread != currentThread){
                System.out.println("  "+Thread.currentThread().getName() + " wait " + msg);
                try { wait();
                } catch (InterruptedException e) { }
            }
            isLocked = true;
            lockedCount++;
            currentLockedThread = currentThread;
        }
        public synchronized void unlock(String msg){
            if(Thread.currentThread() == this.currentLockedThread){
                lockedCount--;
                if(lockedCount == 0){
                    System.out.println("  "+Thread.currentThread().getName() + " notify " + msg);
                    notify();
                    isLocked = false;
                }
            }
        }
    }
    static class Reentrant{
        Lock lock = new Lock();
        public void outer(){
            lock.lock("outer");
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " outer()");
            inner();
            lock.unlock("outer");
        }
        public void inner() {
            lock.lock("inner");
            try { Thread.sleep(100);
            } catch (InterruptedException e) { }
            System.out.println(Thread.currentThread().getName() + " inner()");
            lock.unlock("inner");
        }
    }
    public static void main( String[] args )
    {
        final Reentrant reentrant = new Reentrant();
        int threadCount = 3;
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_" + i){
                public void run(){
                    reentrant.outer();
                }
            }.start();
        }
    }
}

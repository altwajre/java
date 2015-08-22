package com.company.app;

public class App
{
    static long duration = 500;
    static int syncedCount = 1;
    static class SyncedCounter{
        public synchronized void increase() throws InterruptedException {  // BLOCK THREADS
            Thread.sleep(duration);
            System.out.println(Thread.currentThread().getName() + ": " + syncedCount);
            syncedCount++;  // critical section
        }
    }
    private static void testSyncedCounter() {
        final SyncedCounter counter = new SyncedCounter();
        for(int i = 0; i < 5; i++){
            String threadName = "Thread_" + i;
            new Thread(threadName){
                public void run(){
                    try {
                        counter.increase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    public static void main( String[] args )
    {
//        testSyncedCounter();

        final LockCounter counter = new LockCounter();
        for(int i = 0; i < 3; i++){
            String threadName = "Thread_" + i;
            new Thread(threadName){
                public void run(){
                    try {
                        counter.increase();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
    static int lockCount = 1;
    static class LockCounter {
        Lock lock = new Lock();
        public void increase() throws InterruptedException {
            System.out.println("increase() has lock() and unlock() " + Thread.currentThread().getName());
            lock.lock();  // BLOCK THREADS
            Thread.sleep(duration);
            lockCount++;  // critical section
            lock.unlock();
        }
    }
    static class Lock{
        private boolean isLocked = false;
        public synchronized void lock() throws InterruptedException {
            Thread.sleep(duration);
            System.out.println("  lock() " + Thread.currentThread().getName());
            while(isLocked){
                System.out.println("  before wait() " + Thread.currentThread().getName());
                wait();
                System.out.println("    after wait() " + Thread.currentThread().getName());
            }
            isLocked = true;
        }
        public synchronized void unlock() throws InterruptedException {
            Thread.sleep(duration);
            System.out.println("    unlock() " + Thread.currentThread().getName());
            isLocked = false;
            notify();
        }
    }
}

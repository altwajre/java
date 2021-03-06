package com.company.app;

/*

Lock: A lock in Java is owned by the same thread which locked it, so we need Lock.lockingThread member.
- wait() and notify can signal between two threads
- use while(isLocked) to wait, and the first thread does not need to wait to get into critical section,
  other threads wait for notify from other threads

output:
Thread_A_2: lock wait
Thread_A_3: lock wait
Thread_B_1: lock wait
Thread_B_2: lock wait
Thread_B_3: lock wait
  Thread_A_1: unlock notify
    Thread_A_1 finished; count: 1
  Thread_A_2: unlock notify
    Thread_A_2 finished; count: 2
  Thread_A_3: unlock notify
    Thread_A_3 finished; count: 3
  Thread_B_1: unlock notify
    Thread_B_1 finished; count: 4
  Thread_B_2: unlock notify
    Thread_B_2 finished; count: 5
  Thread_B_3: unlock notify
    Thread_B_3 finished; count: 6

 */
public class App 
{
    static class Counter {
        public int count = 0;
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("    " + Thread.currentThread().getName() + " finished; count: " + count);
        }
    }
    static class Lock{  // wait and notify signal
        boolean isLocked = false;
        Thread lockingThread;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + ": lock wait");
                        wait();
                    } catch (InterruptedException e) { }
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
                System.out.println("  " + Thread.currentThread().getName() + ": unlock notify");
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args )
    {
        int threadCount = 3;
        final Counter counter = new Counter();

        for(int i = 1; i <= threadCount; i++) {
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }

        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
    }
}

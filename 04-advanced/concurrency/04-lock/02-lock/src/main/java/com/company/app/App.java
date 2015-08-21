package com.company.app;

/*
Lock:
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
    static int count = 0;
    static class Counter {
        Lock lock = new Lock();

        public void increase(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            count++;
            lock.unlock();
            System.out.println("    " + Thread.currentThread().getName() + " finished; count: " + count);
        }
    }
    static class Lock{
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + ": lock wait");
                        wait();
//                        System.out.println(Thread.currentThread().getName() + ": lock after wait");
                    } catch (InterruptedException e) { }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
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
                    counter.increase();
                }
            }.start();
        }

        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increase();
                }
            }.start();
        }
    }
}

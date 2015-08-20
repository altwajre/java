package com.company.app;

/*
Lock:
1, only the first thread can access the critical section
2, all other threads are waiting
3, after the thread finish the work in critical section will notify one of waiting threads and let it get into critical section.
4, repeat step 3 for all waiting threads

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

        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increase();
                    System.out.println("    " + Thread.currentThread().getName() + " finished; count: " + count);
                }
            }.start();
        }

        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_B_" + i){
                public void run(){
                    counter.increase();
                    System.out.println("    " + Thread.currentThread().getName() + " finished; count: " + count);
                }
            }.start();
        }
    }
}

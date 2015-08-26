package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/locks.html

output:
1
2
3

 */
public class App 
{
    static class Counter{
        public int count = 0;
        Lock lock = new Lock();
        public int inc(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) { }
            int temp = ++count;
            lock.unlock();
            return temp;
        }
    }
    static class Lock{
        boolean isLocked = false;
        public synchronized void lock(){
            while(isLocked){
                try {
                    wait();
                } catch (InterruptedException e) { }
            }
            isLocked = true;
        }
        public synchronized void unlock(){
            notify();
            isLocked = false;
        }
    }
    public static void main( String[] args )
    {
        int threadCount = 3;
        final Counter counter = new Counter();

        for(int i = 1; i <= threadCount; i++) {
            new Thread("Thread_A_" + i){
                public void run(){
                    System.out.println(counter.inc());
                }
            }.start();
        }
    }
}

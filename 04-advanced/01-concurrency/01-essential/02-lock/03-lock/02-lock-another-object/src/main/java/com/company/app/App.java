package com.company.app;

/*
Lock another object inside of Lock of "this"
Note: A lock in Java is owned by the same thread which locked it, so we need Lock.lockingThread member.

output:
6

 */
public class App 
{
    static class Counter{
        public int count = 0;
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {  }
            count++;
            lock.unlock();
        }
    }
    static class QueueObject{ }
    static class Lock{
        QueueObject queueObject = new QueueObject();
        boolean isLocked = false;
        Thread lockingThread;
        public void lock(){
            synchronized (queueObject){  // NOTE: lock queueObject
                while(isLocked){
                    try {
                        queueObject.wait();
                    } catch (InterruptedException e) { }
                }
                isLocked = true;
                lockingThread = Thread.currentThread();
            }
        }
        public void unlock(){
            synchronized (queueObject){   // NOTE: lock queueObject
                if(lockingThread != Thread.currentThread()){
                    throw new IllegalMonitorStateException("Calling thread has not locked this lock");
                }
                queueObject.notify();
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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        System.out.println(counter.count);
    }
}

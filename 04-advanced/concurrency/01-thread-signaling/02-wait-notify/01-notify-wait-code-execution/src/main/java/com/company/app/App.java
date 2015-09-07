package com.company.app;

/*
code after wait() will execute after notify critical section code finsihed.

output:
lockThread: wait()
unlockThread: after notify() before sleep 2 seconds
unlockThread: after notify() after sleep 2 seconds
lockThread: Exit wait()

 */
public class App 
{
    static class Lock {
        public void lock() throws InterruptedException {
            synchronized (this){
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName+": wait()");
                wait();
                System.out.println(threadName+": Exit wait()");
            }
        }
        public void unlock(){
            synchronized (this){
                String threadName = Thread.currentThread().getName();
                notify();
                System.out.println(threadName+": after notify() before sleep 2 seconds");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName+": after notify() after sleep 2 seconds");

            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        new Thread("lockThread"){
            public void run(){
                try {
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread("unlockThread"){
            public void run(){
                lock.unlock();
            }
        }.start();
    }
}

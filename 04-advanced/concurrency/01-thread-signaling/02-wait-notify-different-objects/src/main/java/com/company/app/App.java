package com.company.app;

/*
Two threads wait and notify on different objects will cause exception below

output:
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	at java.lang.Object.wait(Native Method)
	at java.lang.Object.wait(Object.java:502)
	at com.company.app.App$Lock.lock(App.java:19)
	at com.company.app.App$1.run(App.java:36)
lock
Exception in thread "Thread-1" java.lang.IllegalMonitorStateException
	at java.lang.Object.notify(Native Method)
	at com.company.app.App$Lock.unlock(App.java:25)
	at com.company.app.App$2.run(App.java:51)
unlock

 */
public class App
{
    static class Lock {
        public void lock() throws InterruptedException {
            Object monitorObject = new Object();
            synchronized (this){
                monitorObject.wait();
            }
        }
        public void unlock(){
            Object monitorObject = new Object();
            synchronized (this){
                monitorObject.notify();
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock = new Lock();
        Thread lockThread = new Thread(){
            public void run(){
                System.out.println("lock");
                try {
                    lock.lock();
                } catch (InterruptedException e) { }
                System.out.println("lockThread finished");
            }
        };
        lockThread.start();

        Thread unlockThread = new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("unlock");
                lock.unlock();
                System.out.println("unlockThread finished");
            }
        };
        unlockThread.start();
    }
}

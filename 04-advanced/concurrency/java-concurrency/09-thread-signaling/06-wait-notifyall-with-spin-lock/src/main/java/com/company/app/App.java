package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

When using spin lock, only one of wait threads is allowed to continue after notifyAll

output:
Notify_Thread before notify; SharedSignal.wasSignalled=false
Notify_Thread after notify; SharedSignal.wasSignalled=true
Waiting_Thread_2 got data; SharedSignal.wasSignalled=false

 */
public class App
{
    static class MonitorObject{}
    static class WaitNotify { // wait and notify
        public boolean wasSignalled = false;
        MonitorObject monitorObject = new MonitorObject();
        public void doWait(){
            synchronized (monitorObject){
                while(!wasSignalled){ // spin lock
                    try { monitorObject.wait(); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
                // clear signal and continue running
                wasSignalled = false;
            }
        }
        public void doNotify(){
            synchronized (monitorObject){
                wasSignalled = true;
                monitorObject.notifyAll();  // notify all
            }
        }
    }
    public static void main( String[] args )
    {
        final WaitNotify waitNotify = new WaitNotify();
        String threadName = "Notify_Thread";
        new Thread(threadName){
            public void run(){
                try { Thread.sleep(1000); }
                catch (InterruptedException e) { e.printStackTrace(); }
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " before notify; SharedSignal.wasSignalled=" + waitNotify.wasSignalled);
                waitNotify.doNotify();
                System.out.println(threadName + " after notify; SharedSignal.wasSignalled=" + waitNotify.wasSignalled);
            }
        }.start();
        threadName = "Waiting_Thread_1";
        new Thread(threadName){
            public void run(){
                waitNotify.doWait();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " got data; SharedSignal.wasSignalled=" + waitNotify.wasSignalled);
            }
        }.start();
        threadName = "Waiting_Thread_2";
        new Thread(threadName){
            public void run(){
                waitNotify.doWait();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " got data; SharedSignal.wasSignalled=" + waitNotify.wasSignalled);
            }
        }.start();
    }
}

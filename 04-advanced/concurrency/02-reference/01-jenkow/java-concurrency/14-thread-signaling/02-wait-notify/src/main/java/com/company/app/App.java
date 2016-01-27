package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

output:
Notify_Thread before notify; SharedSignal.isReady=false
Notify_Thread after notify; SharedSignal.isReady=true
Waiting_Thread got data; SharedSignal.isReady=true

 */
public class App 
{
    static class MonitorObject{}
    static class SharedSignal { // wait and notify
        public boolean isReady = false;
        MonitorObject monitorObject = new MonitorObject();
        public void doWait(){
            synchronized (monitorObject){
                try { monitorObject.wait(); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        public void doNotify(){
            synchronized (monitorObject){
                isReady = true;
                monitorObject.notify();
            }
        }
    }
    public static void main( String[] args )
    {
        final SharedSignal sharedSignal = new SharedSignal();
        String threadName = "Notify_Thread";
        new Thread(threadName){
            public void run(){
                try { Thread.sleep(1000); }
                catch (InterruptedException e) { e.printStackTrace(); }
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " before notify; SharedSignal.isReady=" + sharedSignal.isReady );
                sharedSignal.doNotify();
                System.out.println(threadName + " after notify; SharedSignal.isReady=" + sharedSignal.isReady);
            }
        }.start();
        threadName = "Waiting_Thread";
        new Thread(threadName){
            public void run(){
                sharedSignal.doWait();
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " got data; SharedSignal.isReady=" + sharedSignal.isReady);
            }
        }.start();
    }
}

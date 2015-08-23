package com.company.app;

/*
Problem:
1, if a thread calls notify() before the thread calls wait(), the waiting thread will miss signal, so waiting thread may
   waiting forever.

Solution:
1, to avoid losing signals, add a member variable isNotified in the MonitorObject, so a thread will only call wait()
   when isNotified=false ensure notify() will happen after wait()

output:
wait
notify
wait finished

 */
public class App 
{
    static class MonitorObject {
        boolean isNotified = false;
        public void doWait() throws InterruptedException {
            synchronized (this){
                if(!isNotified){
                    wait();
                }
                isNotified = false; // Clear the notify signal and continue running
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();
                isNotified = true;
            }
        }
    }
    public static void main( String[] args )
    {
        final MonitorObject monitorObject = new MonitorObject();
        Thread waitThread = new Thread(){
            public void run(){
                // wait
                try {
                    System.out.println("wait");
                    monitorObject.doWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait finished");
            }
        };
        waitThread.start();

        Thread notifyThread = new Thread(){
            public void run(){
                // notify
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("notify");
                monitorObject.doNotify();
            }
        };
        notifyThread.start();
    }
}

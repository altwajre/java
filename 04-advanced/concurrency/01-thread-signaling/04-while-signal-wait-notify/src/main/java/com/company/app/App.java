package com.company.app;

/*
while loop is a spin lock

Problem: Spurious Wakeups
1, it is possible for threads to wake up without notify()

Solution:
1, use while loop instead of if-statement to guard against spurious wakeup.

output:
wait
notify
wait finished

 */
public class App
{
    static class MonitorObject {
        boolean isSignalled = false;
        public void doWait() throws InterruptedException {
            synchronized (this){
                while(!isSignalled){ // NOTE: spin lock
                    wait();
                }
                isSignalled = false;
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();
                isSignalled = true;
            }
        }
    }
    public static void main( String[] args )
    {
        final MonitorObject monitorObject = new MonitorObject();
        Thread waitThread = new Thread(){
            public void run(){
                System.out.println("wait");
                try {
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

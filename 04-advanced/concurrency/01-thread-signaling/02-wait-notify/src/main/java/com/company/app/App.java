package com.company.app;

/*
One wait-thread and one notify-thread by using one MonitorObject

output:
wait
notify
wait finished

 */
public class App 
{
    static class MonitorObject {
        public void doWait() throws InterruptedException {
            synchronized (this){
                wait();
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();
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

package com.company.app;

/*
Spin lock

output:
wait
notify
wait finished

 */
public class App
{
    static class Signal {
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
        final Signal signal = new Signal();
        Thread waitThread = new Thread(){
            public void run(){
                System.out.println("wait");
                try {
                    signal.doWait();
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
                signal.doNotify();
            }
        };
        notifyThread.start();
    }
}

package com.company.app;

/*
output:
wait
notify
wait finished

 */
public class App 
{
    static class Singal{
        public void doWait() throws InterruptedException {
            synchronized (this){
                wait();
            }
            System.out.println("wait finished");
        }
        public void doNotify(){
            synchronized (this){
                notify();
            }
        }
    }
    public static void main( String[] args )
    {
        final Singal singal = new Singal();
        Thread waitThread = new Thread(){
            public void run(){
                // wait
                System.out.println("wait");
                try {
                    singal.doWait();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
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
                singal.doNotify();
            }
        };
        notifyThread.start();
    }
}

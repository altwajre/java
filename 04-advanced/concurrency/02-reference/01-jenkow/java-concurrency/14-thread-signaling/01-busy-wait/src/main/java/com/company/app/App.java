package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/thread-signaling.html

output:
BUSY waiting
BUSY waiting
BUSY waiting
BUSY waiting
BUSY waiting
BUSY waiting
Notify_Thread set data
Waiting_Thread got data

 */
public class App
{
    static class SharedSignal {
        protected boolean hasDataToProcess = false;
        public synchronized boolean isHasDataToProcess(){
            return this.hasDataToProcess;
        }
        public synchronized void setHasDataToProcess(boolean hasData){
            this.hasDataToProcess = hasData;
        }
    }
    public static void main( String[] args )
    {
        final SharedSignal sharedSignal = new SharedSignal();

        String threadName = "Notify_Thread";
        new Thread(threadName){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedSignal.setHasDataToProcess(true);
                System.out.println(Thread.currentThread().getName() + " set data");
            }
        }.start();

        threadName = "Waiting_Thread";
        new Thread(threadName){
            public void run(){
                while(!sharedSignal.isHasDataToProcess()){
                    // do nothing... BUSY waiting
                    System.out.println("BUSY waiting");
                }
                System.out.println(Thread.currentThread().getName() + " got data");
            }
        }.start();
    }
}

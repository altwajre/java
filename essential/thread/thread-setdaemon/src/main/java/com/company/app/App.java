package com.company.app;

/*
Thread.setDaemon()

When setDaemon(false) which is default value, the worker thread continues to run when the main thread terminates
    output:
    Hello from worker 0
    Hello from worker 1
    Main Thread ending
    Hello from worker 2
    Hello from worker 3

When setDaemon(true), the worker thread terminates when the main thread terminates
    output:
    Hello from worker 0
    Hello from worker 1
    Main Thread ending
    Process finished with exit code 0

 */
public class App 
{
    static class WorkerThread extends Thread{
        public WorkerThread(){
            setDaemon(true);  // NOTE: setDaemon() is here
        }
        public void run(){
            int count = 0;
            while(true){
                System.out.println("Hello from worker " + count++);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main( String[] args )
    {
        new WorkerThread().start();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread ending");
    }
}

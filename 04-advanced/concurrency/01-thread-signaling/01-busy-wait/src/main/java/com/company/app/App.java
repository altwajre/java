package com.company.app;

/*
output:
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Busy waiting
Notify_Thread notified - finished
Waiting_Thread done waiting - finished

 */
public class App 
{
    static boolean isNotified = false;
    public static void main( String[] args )
    {
        String threadName = "Waiting_Thread";
        new Thread(threadName){
            public void run(){
                while(!isNotified){
                    // do nothing... Busy waiting
                    System.out.println("Busy waiting");
                }
                System.out.println(Thread.currentThread().getName() + " done waiting - finished");
            }
        }.start();

        threadName = "Notify_Thread";
        new Thread(threadName){
            public void run(){
                sleep(1);
                isNotified = true;
                System.out.println(Thread.currentThread().getName() + " notified - finished");
            }
            private void sleep(int duration) {
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

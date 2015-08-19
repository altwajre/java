package com.company.app;

/*
output:
Waiting_Thread_1 before wait
Waiting_Thread_4 before wait
Waiting_Thread_3 before wait
Waiting_Thread_2 before wait
  Notify_Thread_1 notify
  Notify_Thread_2 notify
  Waiting_Thread_1 after wait
  Waiting_Thread_4 after wait
    Waiting_Thread_4 finished
    Waiting_Thread_1 finished

 */
public class App
{
    static class Signal{
        public void doWait() throws InterruptedException {
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " before wait");
                wait();  // exception occurs when not using synchronized
                System.out.println("  " + Thread.currentThread().getName() + " after wait");
            }
        }
        public void doNotify(){
            synchronized (this){
                notify();  // exception occurs when not using synchronized
            }
        }
    }
    static Signal signal = new Signal();
    public static void main( String[] args )
    {
        waitThreads();

        notifyThreads();
    }
    private static void waitThreads() {
        String threadName = "Waiting_Thread_";
        for(int i = 1; i <= 4; i++){
            new Thread(threadName + i){
                public void run(){
                    try { signal.doWait(); } catch (Exception e) {}
                    System.out.println("    " + Thread.currentThread().getName() + " finished");
                }
            }.start();
        }
    }
    private static void notifyThreads() {
        String threadName = "Notify_Thread_";
        for(int i = 1; i <= 2; i++){
            new Thread(threadName + i){
                public void run(){
                    try { Thread.sleep(100); } catch (Exception e) {}
                    System.out.println("  " + Thread.currentThread().getName() + " notify");
                    signal.doNotify();
                }
            }.start();
        }
    }
}

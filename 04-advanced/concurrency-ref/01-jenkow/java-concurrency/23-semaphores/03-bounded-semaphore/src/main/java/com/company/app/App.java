package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/semaphores.html

take() method blocks if the number of signals is equal to the upper bound.

output:
  Thread_1: take() notify; Semaphore.signals=1
Thread_1: main() semaphore acquired; Semaphore.signals=1
  Thread_4: take() notify; Semaphore.signals=2
Thread_4: main() semaphore acquired; Semaphore.signals=2
Thread_3: take() wait; Semaphore.signals=2
Thread_2: take() wait; Semaphore.signals=2
Thread_5: take() wait; Semaphore.signals=2
Thread_1: main() semaphore acquired; finished task
  Thread_1: release() notify; Semaphore.signals=1
Thread_4: main() semaphore acquired; finished task
  Thread_4: release() notify; Semaphore.signals=0
  Thread_3: take() notify; Semaphore.signals=1
Thread_3: main() semaphore acquired; Semaphore.signals=1
  Thread_5: take() notify; Semaphore.signals=2
Thread_5: main() semaphore acquired; Semaphore.signals=2
Thread_2: take() wait; Semaphore.signals=2
Thread_5: main() semaphore acquired; finished task
Thread_3: main() semaphore acquired; finished task
  Thread_5: release() notify; Semaphore.signals=1
  Thread_2: take() notify; Semaphore.signals=2
Thread_2: main() semaphore acquired; Semaphore.signals=2
  Thread_3: release() notify; Semaphore.signals=1
Thread_2: main() semaphore acquired; finished task
  Thread_2: release() notify; Semaphore.signals=0

 */
public class App 
{
    static class BoundedSemaphore{
        public int signals = 0;
        public int bound = 0;
        public BoundedSemaphore(int upperBound){ this.bound = upperBound; }
        public synchronized void take() {
            String threadName = Thread.currentThread().getName();
            while(this.signals == bound){
                try {
                    System.out.println(threadName + ": take() wait; Semaphore.signals=" + signals);
                    wait();
                } catch (InterruptedException e) { }
            }
            signals++;
            System.out.println("  "+threadName + ": take() notify; Semaphore.signals=" + signals);
            notify();
        }
        public synchronized void release() {
            String threadName = Thread.currentThread().getName();
            while(signals == 0){
                try {
                    System.out.println(threadName + ": release() wait; Semaphore.signals=" + signals);
                    wait();
                } catch (InterruptedException e) {  }
            }
            signals--;
            System.out.println("  "+threadName + ": release() notify; Semaphore.signals=" + signals);
            notify();
        }
    }
    public static void main( String[] args )
    {
        final BoundedSemaphore semaphore = new BoundedSemaphore(2);
        for(int i = 1; i <= 5; i++){
            new Thread("Thread_" + i){
                public void run(){
                    String threadName = Thread.currentThread().getName();
                    try{
                        semaphore.take();
                        try {
                            System.out.println(threadName + ": main() semaphore acquired; Semaphore.signals=" + semaphore.signals);
                            Thread.sleep(1000);
                            System.out.println(threadName + ": main() semaphore acquired; finished task");
                        } catch (InterruptedException e) {  }
                    }
                    finally {
                        semaphore.release();
                    }
                }
            }.start();
        }
    }
}

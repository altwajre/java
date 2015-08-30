package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/semaphores.html

Counting Semaphore count number of signals sent to the other thread.

For example, like a person is waiting to eat egg that is cooked by another person
1, Thread eat-egg person is waiting
2, Thread cook-egg person need 1 second to cook an egg, cook-egg person will increase cooked-egg count to
   let other person how many eggs are ready
3, Thread eat-egg person need 2.5 seconds to eat an egg
4, Since cook-egg is faster than eat-egg, so the eat-egg person only need to wait for the first egg
   and keep eating another egg after finish one because cook is faster than eat.
5, Therefore, cooked-egg count is keep increasing

output:
Thread_CookEgg: before cook egg; Semaphore.signals=0
  Thread_EatEgg: wait(); signals=0
Thread_CookEgg: after cook egg; Semaphore.signals=0
Thread_CookEgg: notify(); signals=1
  Thread_EatEgg: exit release(); signals=0
  Thread_EatEgg: before eat egg; Semaphore.signals=0

Thread_CookEgg: before cook egg; Semaphore.signals=0
Thread_CookEgg: after cook egg; Semaphore.signals=0
Thread_CookEgg: notify(); signals=1

Thread_CookEgg: before cook egg; Semaphore.signals=1
Thread_CookEgg: after cook egg; Semaphore.signals=1
Thread_CookEgg: notify(); signals=2

Thread_CookEgg: before cook egg; Semaphore.signals=2
  Thread_EatEgg: after eat egg; Semaphore.signals=2
  Thread_EatEgg: exit release(); signals=1
  Thread_EatEgg: before eat egg; Semaphore.signals=1
Thread_CookEgg: after cook egg; Semaphore.signals=1
Thread_CookEgg: notify(); signals=2

Thread_CookEgg: before cook egg; Semaphore.signals=2
Thread_CookEgg: after cook egg; Semaphore.signals=2
Thread_CookEgg: notify(); signals=3

Thread_CookEgg: before cook egg; Semaphore.signals=3
  Thread_EatEgg: after eat egg; Semaphore.signals=3
  Thread_EatEgg: exit release(); signals=2
  Thread_EatEgg: before eat egg; Semaphore.signals=2
Thread_CookEgg: after cook egg; Semaphore.signals=2
Thread_CookEgg: notify(); signals=3

Thread_CookEgg: before cook egg; Semaphore.signals=3
Thread_CookEgg: after cook egg; Semaphore.signals=3
Thread_CookEgg: notify(); signals=4

Thread_CookEgg: before cook egg; Semaphore.signals=4
  Thread_EatEgg: after eat egg; Semaphore.signals=4
  Thread_EatEgg: exit release(); signals=3
  Thread_EatEgg: before eat egg; Semaphore.signals=3
Thread_CookEgg: after cook egg; Semaphore.signals=3
Thread_CookEgg: notify(); signals=4

Thread_CookEgg: before cook egg; Semaphore.signals=4
Thread_CookEgg: after cook egg; Semaphore.signals=4
Thread_CookEgg: notify(); signals=5

 */
public class App 
{
    static class CountingSemaphore{
        public int signals = 0;
        public synchronized void take(){
            this.signals++;
            System.out.println(Thread.currentThread().getName() + ": notify(); signals=" + signals);
            this.notify();
        }
        public synchronized void release(){
            while(this.signals == 0){
                try {
                    System.out.println("  "+Thread.currentThread().getName() + ": wait(); signals=" + signals);
                    wait();
                } catch (InterruptedException e) { }
            }
            this.signals--;
            System.out.println("  "+Thread.currentThread().getName() + ": exit release(); signals=" + signals);
        }
    }
    public static void main( String[] args )
    {
        final CountingSemaphore semaphore = new CountingSemaphore();
        Thread cookEggThread = new Thread("Thread_CookEgg"){
            public void run(){
                while(true){
                    String threadName = Thread.currentThread().getName();
                    try {
                        System.out.println("\n"+threadName + ": before cook egg; Semaphore.signals=" + semaphore.signals);
                        Thread.sleep(1000);  // do something - cook egg
                        System.out.println(threadName + ": after cook egg; Semaphore.signals=" + semaphore.signals);
                    } catch (InterruptedException e) { }
                    semaphore.take();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {  }
                }
            }
        };
        cookEggThread.start();
        Thread eatThread = new Thread("Thread_EatEgg"){
            public void run(){
                while(true){
                    String threadName = Thread.currentThread().getName();
                    semaphore.release();
                    try {
                        System.out.println("  "+threadName + ": before eat egg; Semaphore.signals=" + semaphore.signals);
                        Thread.sleep(2500);  // do something - eat egg
                        System.out.println("  "+threadName + ": after eat egg; Semaphore.signals=" + semaphore.signals);
                    } catch (InterruptedException e) { }
                }
            }
        };
        eatThread.start();
    }
}

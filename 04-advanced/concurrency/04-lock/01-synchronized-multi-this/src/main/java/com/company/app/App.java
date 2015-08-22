package com.company.app;

/*
When you "synchronized (this)" or "synchronized void lock()", you lock the whole object.
Only one thread can access "synchronized (this)" at a time.

output:
Thread_1 wait 1
Thread_2 wait 1
Thread_3 wait 1
  Thread_1 enter synced 1
Thread_1 wait 2
  Thread_3 enter synced 1
Thread_3 wait 2
  Thread_2 enter synced 1
Thread_2 wait 2
  Thread_3 enter synced 2
Thread_3 wait 3
  Thread_1 enter synced 2
Thread_1 wait 3
  Thread_3 enter synced 3
        Thread_3 end
  Thread_2 enter synced 2
Thread_2 wait 3
  Thread_1 enter synced 3
        Thread_1 end
  Thread_2 enter synced 3
        Thread_2 end
 */
public class App 
{
    static class SyncClass{
        public void test() throws InterruptedException {
            for(int i = 1; i <= 3; i++){
                System.out.println(Thread.currentThread().getName() + " wait " + i);
                synchronized (this){
                    System.out.println("  "+Thread.currentThread().getName() + " enter synced " + i);
                    Thread.sleep(1000);
                }
            }
            System.out.println("        "+Thread.currentThread().getName() + " end");
        }
    }
    public static void main( String[] args ) throws InterruptedException {
        int threadCount = 3;
        final SyncClass syncClass = new SyncClass();
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_" + i){
                public void run(){
                    try {
                        syncClass.test();
                    } catch (InterruptedException e) { }
                }
            }.start();
        }
    }
}

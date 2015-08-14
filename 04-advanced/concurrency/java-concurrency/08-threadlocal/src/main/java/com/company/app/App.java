package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/threadlocal.html
Enable you to create variables that can only be read and written to the same thread.

output:
Thread-0 set 47
Thread-1 set 51
Thread-1 get 51
Thread-0 get 47

 */
public class App 
{
    static class MyRunnale implements Runnable{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();  // ThreadLocal
        public void run() {
            int value = (int)(Math.random() * 100D);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " set " + value);
            threadLocal.set(value);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " get " + threadLocal.get());
        }
    }
    public static void main( String[] args )
    {
        MyRunnale sharedRunnableInstance = new MyRunnale();
        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();
    }
}

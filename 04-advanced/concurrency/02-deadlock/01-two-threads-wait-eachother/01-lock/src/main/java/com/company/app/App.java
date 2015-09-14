package com.company.app;

/*
Thread 1 locks A, waits for B
Thread 2 locks B, waits for A

output:
Thread_1 locked lock_A
Thread_2 locked lock_B
Thread_1 try to lock lock_B
Thread_2 try to lock lock_A
Thread_2 lock_A.wait()
Thread_1 lock_B.wait()

 */
public class App 
{
    static class Lock{
        public Lock(String name){ this.name = name; }
        public String name;
        boolean isLocked = false;
        public void lock(){
            synchronized (this){
                while(isLocked){
                    try {
                        System.out.println(Thread.currentThread().getName() + " " + name + ".wait()");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isLocked = true;
            }
        }
        public void unlock(){
            synchronized (this){
                System.out.println(Thread.currentThread().getName() + " " + name + ".notify()");
                notify();
                isLocked = false;
            }
        }
    }
    public static void main( String[] args )
    {
        final Lock lock_A = new Lock("lock_A");
        final Lock lock_B = new Lock("lock_B");

        new Thread("Thread_1"){
            public void run(){
                String threadName = Thread.currentThread().getName();
                lock_A.lock();
                System.out.println(threadName + " locked " + lock_A.name);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " try to lock " + lock_B.name);
                lock_B.lock();
                System.out.println(threadName + " locked " + lock_B.name);

                lock_B.unlock();
                lock_A.unlock();
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                String threadName = Thread.currentThread().getName();
                lock_B.lock();
                System.out.println(threadName + " locked " + lock_B.name);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " try to lock " + lock_A.name);
                lock_A.lock();
                System.out.println(threadName + "locked " + lock_A.name);

                lock_A.unlock();
                lock_B.unlock();
            }
        }.start();
    }
}

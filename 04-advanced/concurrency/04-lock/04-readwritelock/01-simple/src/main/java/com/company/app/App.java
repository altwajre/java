package com.company.app;

/*
http://tutorials.jenkov.com/java-concurrency/read-write-locks.html
https://www.youtube.com/watch?v=nmCfNtK_NzA

lockWrite()
1, writeRequests++
2, wait while(readers > 0 || writers > 0)

lockRead()
1, wait while(writers > 0 || writeRequests > 0)

Problem:
The ReadWriteLock class shown earlier is not reentrant. If a thread that has write access requests it again,
it will block because there is already one writer - itself. Furthermore, consider this case:

1, Thread 1 gets read access.
2, Thread 2 requests write access but is blocked because there is one reader.
3, Thread 1 re-requests read access (re-enters the block), but is blocked because there is a write request.

In this situation the previous ReadWriteLock would lock up - a situation similar to deadlock.
No threads requesting neither read nor write access would be granted so.

To make the ReadWriteLock reentrant it is necessary to make a few changes. Reentrance for readers and writers
will be dealt with separately.

output:
  Thread_Read_1 lockRead(); readers=0; writers=0; writeRequests=0
Thread_Read_1 lockRead() exit while loop
  Thread_Read_2 lockRead(); readers=1; writers=0; writeRequests=0
Thread_Read_2 lockRead() exit while loop
Thread_Write_1 lockWrite(); readers=2; writers=0; writeRequests=1
Thread_Write_1 lockWrite() within while loop; readers=2; writers=0; writeRequests=1
Thread_Write_1 write wait
  Thread_Read_1 read notifyAll
0
Thread_Write_1 lockWrite() within while loop; readers=1; writers=0; writeRequests=1
Thread_Write_1 write wait
  Thread_Read_2 read notifyAll
0
Thread_Write_1 lockWrite() exit while loop
Thread_Write_1 write notifyAll

 */
public class App 
{
    static class Counter{
        public int count = 0;
        ReadWriteLock lock = new ReadWriteLock();
        public void increment() throws InterruptedException {
            lock.lockWrite();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {  }
            count++;
            lock.unlockWrite();
        }
        public int getCount() throws InterruptedException {
            int temp = -1;
            lock.lockRead();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {  }
            temp = count;
            lock.unlockedRead();
            return temp;
        }
    }
    static class ReadWriteLock{
        int readers = 0;
        int writers = 0;
        int writeRequests = 0;
        public synchronized void lockWrite() throws InterruptedException {
            writeRequests++;  // will make lockRead() wait
            System.out.println(Thread.currentThread().getName() + " lockWrite(); readers=" + readers + "; writers="
                    + writers + "; writeRequests=" + writeRequests);
            while(readers > 0 || writers > 0){  // wait if there is reader or writer
                System.out.println(Thread.currentThread().getName() + " lockWrite() within while loop; readers="
                        + readers + "; writers=" + writers + "; writeRequests=" + writeRequests);
                System.out.println(Thread.currentThread().getName() + " write wait");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " lockWrite() exit while loop");
            writeRequests--;
            writers++;
        }
        public synchronized void unlockWrite(){
            writers--;
            System.out.println(Thread.currentThread().getName() + " write notifyAll");
            notifyAll();
        }

        public synchronized void lockRead() throws InterruptedException {
            System.out.println("  " + Thread.currentThread().getName() + " lockRead(); readers=" + readers + "; writers="
                    + writers + "; writeRequests=" + writeRequests);
            while(writers > 0 || writeRequests > 0){  // wait if there is writer or write request
                System.out.println("  "+Thread.currentThread().getName() + " lockRead() within while loop; readers="
                        + readers + "; writers=" + writers + "; writeRequests=" + writeRequests);
                System.out.println("  " + Thread.currentThread().getName() + " read wait");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " lockRead() exit while loop");
            readers++;
        }
        public synchronized void unlockedRead(){
            readers--;
            System.out.println("  "+Thread.currentThread().getName() + " read notifyAll");
            notifyAll();
        }
    }

    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        Thread readThread1 = new Thread("Thread_Read_1"){
            public void run(){
                try {
                    System.out.println(counter.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        readThread1.start();

        Thread writeThread1 = new Thread("Thread_Write_1"){
            public void run(){
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        writeThread1.start();

//        readThread1.start();  // Problem

        Thread readThread2 = new Thread("Thread_Read_2"){
            public void run(){
                try {
                    System.out.println(counter.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        readThread2.start();
    }
}

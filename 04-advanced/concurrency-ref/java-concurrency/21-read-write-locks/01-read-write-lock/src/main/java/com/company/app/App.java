package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
Solution:
lockWrite()
1, writeRequests++
2, wait while(readers > 0 || writers > 0)

lockRead()
1, wait while(writers > 0 || writeRequests > 0)


output:
Thread_1 lockWrite(); readers=0; writers=0; writeRequests=1
Thread_1 lockWrite() exit while loop
Thread_2 lockWrite(); readers=0; writers=1; writeRequests=1
Thread_2 lockWrite() within while loop; readers=0; writers=1; writeRequests=1
Thread_2 write wait
  Thread_3 lockRead(); readers=0; writers=1; writeRequests=1
  Thread_3 lockRead() within while loop; readers=0; writers=1; writeRequests=1
  Thread_3 read wait
Thread_4 lockWrite(); readers=0; writers=1; writeRequests=2
Thread_4 lockWrite() within while loop; readers=0; writers=1; writeRequests=2
Thread_4 write wait
  Thread_5 lockRead(); readers=0; writers=1; writeRequests=2
  Thread_5 lockRead() within while loop; readers=0; writers=1; writeRequests=2
  Thread_5 read wait
Thread_1 after lockWrite - add 1
Thread_1 write notifyAll
  Thread_5 lockRead() within while loop; readers=0; writers=0; writeRequests=2
  Thread_5 read wait
Thread_4 lockWrite() exit while loop
  Thread_3 lockRead() within while loop; readers=0; writers=1; writeRequests=1
  Thread_3 read wait
Thread_2 lockWrite() within while loop; readers=0; writers=1; writeRequests=1
Thread_2 write wait
Thread_4 after lockWrite - add 4
Thread_4 write notifyAll
Thread_2 lockWrite() exit while loop
  Thread_3 lockRead() within while loop; readers=0; writers=1; writeRequests=0
  Thread_3 read wait
  Thread_5 lockRead() within while loop; readers=0; writers=1; writeRequests=0
  Thread_5 read wait
Thread_2 after lockWrite - add 2
Thread_2 write notifyAll
Thread_5 lockRead() exit while loop
Thread_3 lockRead() exit while loop
  Thread_5 after lockRead - get 0
  Thread_5 read notifyAll
  Thread_3 after lockRead - get 1
  Thread_3 read notifyAll

 */
public class App 
{
    static class ReadWriteLock{
        int readers = 0;
        int writers = 0;
        int writeRequests = 0;
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
    }
    static class ThreadSafeArrayList<T>{
        ReadWriteLock readWriteLock = new ReadWriteLock();
        List<T> list = new ArrayList<T>();
        public void add(T t) throws InterruptedException {
            readWriteLock.lockWrite();
            try{
                Thread.sleep(1000);
                list.add(t);
                System.out.println(Thread.currentThread().getName() + " after lockWrite - add "+ t);
            }
            finally { readWriteLock.unlockWrite(); }
        }
        public T get(int i) throws InterruptedException {
            readWriteLock.lockRead();
            try{
                Thread.sleep(1000);
                System.out.println("  " + Thread.currentThread().getName() + " after lockRead - get " + i );
                return list.get(i);
            }
            finally { readWriteLock.unlockedRead(); }
        }
    }
    public static void main( String[] args ) {
        final ThreadSafeArrayList<Integer> threadSafeArrayList = new ThreadSafeArrayList<Integer>();
        new Thread("Thread_1"){
            public void run(){
                try { threadSafeArrayList.add(1); } catch (InterruptedException e) { }
            }
        }.start();

        new Thread("Thread_2"){
            public void run(){
                try { threadSafeArrayList.add(2); } catch (InterruptedException e) { }
            }
        }.start();

        new Thread("Thread_3"){
            public void run(){
                try { threadSafeArrayList.get(1); } catch (InterruptedException e) { }
            }
        }.start();

        new Thread("Thread_4"){
            public void run(){
                try { threadSafeArrayList.add(4); } catch (InterruptedException e) { }
            }
        }.start();

        new Thread("Thread_5"){
            public void run(){
                try { threadSafeArrayList.get(0); } catch (InterruptedException e) { }
            }
        }.start();
    }
}

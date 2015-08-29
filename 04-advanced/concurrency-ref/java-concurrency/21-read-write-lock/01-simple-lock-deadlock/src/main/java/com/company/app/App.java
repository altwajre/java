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
Exception in thread "main" java.lang.IllegalThreadStateException
	at java.lang.Thread.start(Thread.java:705)
  Thread_read lockRead(); readers=0; writers=0; writeRequests=0
	at com.company.app.App.main(App.java:120)
Thread_read lockRead() exit while loop
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
Thread_write lockWrite(); readers=1; writers=0; writeRequests=1
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
Thread_write lockWrite() within while loop; readers=1; writers=0; writeRequests=1
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
Thread_write write wait
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:140)
  Thread_read after lockRead - get 1
  Thread_read read notifyAll
Thread_write lockWrite() exit while loop
Thread_write after lockWrite - add 11
Thread_write write notifyAll

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
    static class ThreadSafeArrayList{
        ReadWriteLock readWriteLock = new ReadWriteLock();
        List<Integer> list = new ArrayList<Integer>();
        public ThreadSafeArrayList(){ // NOTE: pre-populated data
            for(int i = 1; i <= 3; i++){
                list.add(i);
            }
        }
        public void add(Integer t) throws InterruptedException {
            readWriteLock.lockWrite();
            try{
                Thread.sleep(1000);
                list.add(t);
                System.out.println(Thread.currentThread().getName() + " after lockWrite - add "+ t);
            }
            finally { readWriteLock.unlockWrite(); }
        }
        public Integer get(int i) throws InterruptedException {
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
        final ThreadSafeArrayList threadSafeArrayList = new ThreadSafeArrayList();

        // 1, Thread 1 gets read access
        Thread readThread = new Thread("Thread_read"){
            public void run(){
                try { threadSafeArrayList.get(1); } catch (InterruptedException e) { }
            }
        };
        readThread.start();

        // 2, Thread 2 requests write access but is blocked because there is one reader
        Thread writeThread = new Thread("Thread_write"){
            public void run(){
                try { threadSafeArrayList.add(11); } catch (InterruptedException e) { }
            }
        };
        writeThread.start();

        // 3, Thread 1 re-requests read access (re-enters the block), but is blocked because there is a write request.
        readThread.start();
    }
}

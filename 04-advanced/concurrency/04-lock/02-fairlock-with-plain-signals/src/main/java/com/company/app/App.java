package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
GOOD

For how to implement the FairLock, see Slipped Conditions realistic example at link below
http://tutorials.jenkov.com/java-concurrency/slipped-conditions.html

output:
Thread_A_1 added signals: signal.id=1
Thread_A_1 isLocked=false
Thread_A_1 (signals.get(0) != signal) = false; signal.id=1
Thread_A_1 mustWait=false
Thread_A_1 removed signals: signal.id=1
Thread_A_3 added signals: signal.id=2
Thread_A_3 isLocked=true
Thread_A_3 (signals.get(0) != signal) = false; signal.id=2
Thread_A_3 mustWait=true
 Thread_A_3 signal.id=2 wait()
Thread_A_2 added signals: signal.id=3
Thread_A_2 isLocked=true
Thread_A_2 (signals.get(0) != signal) = true; signal.id=3
Thread_A_2 mustWait=true
 Thread_A_2 signal.id=3 wait()
  Thread_A_1 signal.id=2 notify()
Thread_A_3 isLocked=false
Thread_A_3 (signals.get(0) != signal) = false; signal.id=2
Thread_A_3 mustWait=false
Thread_A_3 removed signals: signal.id=2
  Thread_A_3 signal.id=3 notify()
Thread_A_2 isLocked=false
Thread_A_2 (signals.get(0) != signal) = false; signal.id=3
Thread_A_2 mustWait=false
Thread_A_2 removed signals: signal.id=3
3

 */
public class App 
{
    static int count;
    static class Counter{
        Lock lock = new Lock();
        public void increment(){
            lock.lock();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {  }
            count++;
            lock.unlock();
        }
    }
    static int idCount = 1;
    static class Signal {
        public Signal(int id){
            this.id = id;
            idCount++;
        }
        public int id;
    }
    static class Lock{
        List<Signal> signals = new ArrayList<Signal>();
        boolean isLocked = false;
        public void lock(){
            Signal signal;
            synchronized (this){
                signal = new Signal(idCount);
                signals.add(signal);
                System.out.println(Thread.currentThread().getName() + " added signals: signal.id=" + signal.id);
            }
            boolean mustWait = true;
            while (mustWait){
                synchronized (this){
                    mustWait = isLocked || signals.get(0) != signal;
                    System.out.println(Thread.currentThread().getName() + " isLocked=" + isLocked);
                    System.out.println(Thread.currentThread().getName() + " (signals.get(0) != signal) = "
                            + (signals.get(0) != signal) + "; signal.id="+ signal.id);
                    System.out.println(Thread.currentThread().getName() + " mustWait=" + mustWait);
                    if(!mustWait){
                        if(signals.size() > 0){
                            signals.remove(signal);
                            System.out.println(Thread.currentThread().getName() + " removed signals: signal.id=" + signal.id);
                        }
                        isLocked = true;
                        return;
                    }
                }
                synchronized (signal){
                    if(mustWait){
                        try {
                            System.out.println(" "+Thread.currentThread().getName() + " signal.id=" + signal.id + " wait()");
                            signal.wait();
                        } catch (InterruptedException e) {  }
                    }
                }
            }
        }
        public void unlock(){
            if(signals.size() > 0){
                Signal signal = signals.get(0);
                synchronized (signal){
                    System.out.println("  "+Thread.currentThread().getName() + " signal.id=" + signal.id + " notify()");
                    signal.notify();
                    isLocked = false;
                }
            }
        }
    }
    public static void main( String[] args )
    {
        final Counter counter = new Counter();
        int threadCount = 3;
        for(int i = 1; i <= threadCount; i++){
            new Thread("Thread_A_" + i){
                public void run(){
                    counter.increment();
                }
            }.start();
        }
//        for(int i = 1; i <= threadCount; i++){
//            new Thread("Thread_B_" + i){
//                public void run(){
//                    counter.increment();
//                }
//            }.start();
//        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println(count);
    }
}

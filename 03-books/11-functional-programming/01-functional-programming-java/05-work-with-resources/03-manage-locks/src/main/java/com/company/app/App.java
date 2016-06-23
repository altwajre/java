package com.company.app;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Locker{
    public static void runLocked(Lock lock, Runnable block){
        lock.lock();
        try{
            block.run();
        }
        finally {
            lock.unlock();
        }
    }
}
class Locking{
    Lock lock = new ReentrantLock(); // or mock
    protected void setLock(final Lock mock){
        lock = mock;
    }
    // it's verbose, error prone, and hard to maintain
    public void doOp1(){
        lock.lock();
        try{
            System.out.println("doOp1: critical code");
        }
        finally {
            lock.unlock();
        }
    }
    // following methods are concise, less error prone.
    public void doOp2(){
        Locker.runLocked(lock, () -> System.out.println("doOp2: critical code"));
    }
    public void doOp3(){
        Locker.runLocked(lock, () -> System.out.println("doOp3: critical code"));
    }
    public void doOp4(){
        Locker.runLocked(lock, () -> System.out.println("doOp4: critical code"));
    }
}
public class App
{
    public static void main( String[] args )
    {
        Locking locking = new Locking();
        locking.doOp1();
        locking.doOp2();
        locking.doOp3();
        locking.doOp4();
    }
}
/*
output:
doOp1: critical code
doOp2: critical code
doOp3: critical code
doOp4: critical code
 */

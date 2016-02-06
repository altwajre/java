package com.company.app;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
DeadLock due to the locks ordering below

https://www.youtube.com/watch?v=ghCUBi41bGA&index=11&list=PLBB24CFB073F1048E

Thread_1
lock1.lock()
lock2.lock()

Thread_2
lock2.lock()
lock1.lock()

DeadLock when Thread_1 got lock1 and Thread_2 got lock2
Thread_1 is waiting for other thread to unlock lock1
Thread_2 is waiting for other thread to unlock lock2
 */
class Account{
    private int balance = 10000;
    public void deposit(int amount){
        balance += amount;
    }
    public void withdraw(int amount){
        balance -= amount;
    }
    public int getBalance(){
        return balance;
    }
    public static void transfer(Account acc1, Account acc2, int amount){
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
class Runner{
    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    public void firstThread(){
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            lock1.lock();
            lock2.lock();
            try{
                Account.transfer(acc1, acc2, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void secondThread(){
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            lock2.lock();
            lock1.lock();
            try{
                Account.transfer(acc2, acc1, random.nextInt(100));
            }
            finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }
    public void finished(){
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}
public class App
{
    public static void main( String[] args )
    {
        Runner runner = new Runner();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.firstThread();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                runner.secondThread();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.finished();
    }
}
/*
output: DEADLOCK
 */

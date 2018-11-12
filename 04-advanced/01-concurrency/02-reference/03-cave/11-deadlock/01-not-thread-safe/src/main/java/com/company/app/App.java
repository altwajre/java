package com.company.app;

import java.util.Random;

/*
NOT Thread Safe
https://www.youtube.com/watch?v=ghCUBi41bGA&index=11&list=PLBB24CFB073F1048E

Problem:
The total balance is not 20000 for two accounts.
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
    public void firstThread(){
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            Account.transfer(acc1, acc2, random.nextInt(100));
        }
    }
    public void secondThread(){
        Random random = new Random();
        for(int i = 0; i < 10000; i++){
            Account.transfer(acc2, acc1, random.nextInt(100));
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
Account 1 balance: 16961
Account 2 balance: 3533
Total balance: 20494
 */

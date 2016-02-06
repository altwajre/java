package com.company.app;

/*
NOT Thread Safe without using lock

https://www.youtube.com/watch?v=fjMTaVykOpc&list=PLBB24CFB073F1048E&index=10

Problem:
The output is not 20000 due to race condition.
 */
class Runner{
    private int count = 0;
    private void increment(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }
    public void firstThread(){
        increment();
    }
    public void secondThread(){
        increment();
    }
    public void finished(){
        System.out.println("Count is: " + count);
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
output:
Count is: 10506
 */

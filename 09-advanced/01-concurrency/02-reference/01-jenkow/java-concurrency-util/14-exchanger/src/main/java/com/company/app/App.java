package com.company.app;

import java.util.concurrent.Exchanger;

/*
http://tutorials.jenkov.com/java-util-concurrent/exchanger.html

Two threads can exchange objects.
 */
class ExchangerRunnable implements Runnable{
    Exchanger exchanger = null;
    Object object = null;
    public ExchangerRunnable(Exchanger exchanger, Object object){
        this.exchanger = exchanger;
        this.object = object;
    }
    public void run() {
        try {
            Object previous = this.object;
            this.object = this.exchanger.exchange(this.object);
            System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Exchanger exchanger = new Exchanger();
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
    }
}
/*
output:
Thread-0 exchanged A for B
Thread-1 exchanged B for A
 */

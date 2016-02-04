package com.company.app;

import java.util.Scanner;

/*
Shared Between Threads

When the state variable running is volatile, it won't cache the value locally because it thought it is shared between
threads.
The process.shutdown() in the main thread will always work when state variable running won't cached locally.
 */
class Processor extends Thread{
    private volatile boolean running = true; // volatile
    @Override
    public void run(){
        while (running){
            System.out.println(Thread.currentThread().getName() + " says hello");
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    public void shutdown(){
        running = false;
        System.out.println(Thread.currentThread().getName() + " shutdown");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Processor processor = new Processor();
        processor.start();

        System.out.println("Press return to stop...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        processor.shutdown();
    }
}
/*
output:
Press return to stop...
Thread-0 says hello
Thread-0 says hello
Thread-0 says hello

main shutdown
 */

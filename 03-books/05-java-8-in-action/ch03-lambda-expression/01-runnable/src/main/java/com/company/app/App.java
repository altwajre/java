package com.company.app;

/*
functional interface defines only one abstract method
public interface Runnable{
  public abstract void run();
}
 */
public class App
{
    public static void process(Runnable r){
        r.run();
    }
    public static void main( String[] args )
    {
        Runnable r1 = () -> System.out.println("Runnable 1"); // lambda
        Runnable r2 = new Runnable() { // anonymous class
            @Override
            public void run() {
                System.out.println("anonymous class 1");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Runnable 2")); // lambda - pass lambda direct to functional interface
    }
}
/*
output:
Runnable 1
anonymous class 1
Runnable 2
 */

package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
ThreadSafe

Use synchronized to allow only one thread can enter the getInstance() method at a time
 */
class ExpensiveObject{
    @Override
    public String toString(){
        return "obj ";
    }
}
class LazyInitRace{
    private ExpensiveObject instance = null;
    public synchronized ExpensiveObject getInstance(){
        try {
            Thread.sleep(10);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        if(instance == null){
            instance = new ExpensiveObject();
        }
        return instance;
    }
}
public class App
{
    public static void main( String[] args )
    {
        final LazyInitRace lazyInitRace = new LazyInitRace();
        List<ExpensiveObject> list = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    ExpensiveObject temp = lazyInitRace.getInstance();
                    list.add(temp);
                }
            }.start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        list.forEach(System.out::print);

        System.out.println("");

        final int[] count = {0};
        final ExpensiveObject[] firstNonNull = new ExpensiveObject[1];
        list.forEach(e -> {
            if(e != null){
                firstNonNull[0] = e;
            }
        });
        if(firstNonNull[0] == null) throw new NullPointerException("Error: first is null");
        list.forEach(e -> {
            if(e != firstNonNull[0]){
                System.out.println(++count[0] + " different instance object");
            }
        });
    }
}
/*
output:
obj obj obj obj obj obj obj obj obj obj
 */

package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
A common idiom that uses check-then-act is lazy initialization.
LazyInitRace has race conditions that can undermine its correctness. Say that threads A and B execute getInstance at the
same time. A sees that instance is null, and instantiates a new ExpressiveObject. B also checks if instance is null.
Whether instance is null at this point depends unpredictably on timing, including the vagaries of scheduling and how
long A takes to instantiates the ExpressiveObject and set the instance field. If instance is null when B examines it,
the two callers to getInstance may receive two different results, even though getInstance is always supposed to return
the same instance.
 */
class ExpensiveObject{
    @Override
    public String toString(){
        return "obj ";
    }
}
// Race Condition in Lazy Initialization. Don't do this.
class LazyInitRace{
    private ExpensiveObject instance = null;
    public ExpensiveObject getInstance(){
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
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
nullnullnullobj obj obj obj obj obj obj
1 different instance object
2 different instance object
3 different instance object
4 different instance object
5 different instance object
6 different instance object
 */

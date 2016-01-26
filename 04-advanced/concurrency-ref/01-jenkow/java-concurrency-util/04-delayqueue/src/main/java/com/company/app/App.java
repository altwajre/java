package com.company.app;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/*
http://tutorials.jenkov.com/java-util-concurrent/delayqueue.html

The value returned by the getDelay() method should be the delay remaining before this element can be released. If 0 or
a negative value is returned, the delay will be considered expired, and the element released at the next take().
 */
class DelayedElement implements Delayed{
    private String data;
    private long startTime;
    public DelayedElement(String data, long delay){
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.startTime < ((DelayedElement)o).startTime){
            return - 1;
        }
        if(this.startTime > ((DelayedElement)o).startTime){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString(){
        return "data=" + data + ", startTime=" + startTime + " ";
    }
}
public class App
{
    public static void main( String[] args )
    {
        DelayQueue queue = new DelayQueue();
        Delayed element1 = new DelayedElement("my_element", 2000);
        queue.put(element1);
        try {
            System.out.println("before queue.take()");
            Delayed element2 = queue.take();
            System.out.println("taken element: "+element2);
            System.out.println("after queue.take()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
output:
before queue.take()
taken element: data=my_element, startTime=1453772222858
after queue.take()
 */

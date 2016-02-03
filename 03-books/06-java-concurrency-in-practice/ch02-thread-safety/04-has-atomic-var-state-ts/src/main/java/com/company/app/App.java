package com.company.app;

import java.util.concurrent.atomic.AtomicLong;

/*
Listing 2.4. Servlet that Counts Requests Using AtomicLong.
 */
class Request{
    private String name;
    public Request(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
class Response{
    private String name;
    public Response(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
/*
By replacing the long counter with an AtomicLong, we ensure that all actions that access the counter state are atomic.
 */
class CountingFactorizer {
    private final AtomicLong count = new AtomicLong(0);
    public long getCount(){
        return count.get();
    }
    public void service(Request req, Response resp){
        try { Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        count.incrementAndGet();
        String name = req.getName();
        resp.setName("modified " + name);
    }
}
public class App
{
    public static void main( String[] args )
    {
        final CountingFactorizer factorizer = new CountingFactorizer();
        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    factorizer.service(new Request("Tom"), new Response(""));
                    System.out.println(Thread.currentThread().getName() + " - count: " + factorizer.getCount());
                }
            }.start();
        }
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("factorizer.getCount(): " + factorizer.getCount());
    }
}
/*
output:
Thread_5 - count: 7
Thread_8 - count: 5
Thread_2 - count: 4
Thread_4 - count: 6
Thread_6 - count: 5
Thread_7 - count: 9
Thread_3 - count: 10
Thread_1 - count: 4
Thread_9 - count: 9
Thread_0 - count: 5
factorizer.getCount(): 10
 */
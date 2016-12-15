package com.company.app;

import java.util.concurrent.atomic.AtomicLong;

/*
Listing 2.6. Servlet that Caches Last Result, But with Unnacceptably Poor Concurrency. Don't do this.

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
class UnsafeCachingFactorizer {
    private final AtomicLong count = new AtomicLong(0);
    private final AtomicLong lastCount = new AtomicLong();
    public long getCount(){
        return count.get();
    }
    public long getLastCount() {return lastCount.get(); }
    public synchronized void service(Request req, Response resp){
        try { Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        lastCount.set(count.get());
        count.incrementAndGet();
        String name = req.getName();
        resp.setName("modified " + name);
    }
}
public class App
{
    public static void main( String[] args )
    {
        final UnsafeCachingFactorizer factorizer = new UnsafeCachingFactorizer();
        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    factorizer.service(new Request("Tom"), new Response(""));
                    System.out.println(Thread.currentThread().getName() + " - count: " + factorizer.getCount());
                }
            }.start();
        }
        try { Thread.sleep(11000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("factorizer.getCount=" + factorizer.getCount() + ", factorizer.getLastCount=" + factorizer.getLastCount());
    }
}
/*
output:
Thread_3 - count: 1
Thread_5 - count: 2
Thread_7 - count: 3
Thread_6 - count: 4
Thread_2 - count: 5
Thread_4 - count: 6
Thread_1 - count: 7
Thread_0 - count: 8
Thread_8 - count: 9
Thread_9 - count: 10
factorizer.getCount=10, factorizer.getLastCount=9
 */

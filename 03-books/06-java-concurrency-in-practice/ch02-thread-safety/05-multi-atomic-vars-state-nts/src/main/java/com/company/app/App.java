package com.company.app;

import java.util.concurrent.atomic.AtomicLong;

/*
Listing 2.5. Servlet that Attempts to Cache its Last Result without Adequate Atomicity. Don't do this.

Unfortunately, this approach does not work. Even though the atomic references are individually thread-safe,
UnsafeCachingFactorizer has race conditions that could make it produce the wrong answer.
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
    public void service(Request req, Response resp){
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
        try { Thread.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("factorizer.getCount=" + factorizer.getCount() + ", factorizer.getLastCount=" + factorizer.getLastCount());
    }
}
/*
output:
Thread_3 - count: 9
Thread_6 - count: 10
Thread_4 - count: 9
Thread_2 - count: 9
Thread_0 - count: 9
Thread_5 - count: 10
Thread_9 - count: 10
Thread_8 - count: 9
Thread_7 - count: 9
Thread_1 - count: 9
factorizer.getCount=10, factorizer.getLastCount=0
 */

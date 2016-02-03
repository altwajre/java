package com.company.app;

/*
Listing 2.2. Servlet that Counts Requests without the Necessary Synchronization. Don't do this.
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
When an object has state,
It is NOT thread safe - race condition
While the increment operation, count++, may look like a single action because of its compact syntax, it is not atomic,
which means that it does not execute as a single, indivisible operation. Instead, it is a shorthand for a sequence of
three discrete operations: fetch the current value, add one to it, and write the new value back. This is an example of
a read-modify-write operation, in which the resulting state is derived from the previous state.
 */
class UnsafeCountingFactorizer{
    private long count = 0;
    public long getCount(){
        return count;
    }
    public void service(Request req, Response resp){
        try {
            Thread.sleep(1000);  // add sleep(1000) to make it easy to reproduce the race condition
        } catch (InterruptedException e) { }
        count++;
        String name = req.getName();
        resp.setName("modified " + name);
    }
}
public class App
{
    public static void main( String[] args )
    {
        final UnsafeCountingFactorizer factorizer = new UnsafeCountingFactorizer();

        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    factorizer.service(new Request("Tom"), new Response(""));
                    System.out.println(Thread.currentThread().getName() + " - count: " + factorizer.getCount());
                }
            }.start();
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("factorizer.getCount(): " + factorizer.getCount());
    }
}
/*
The final count is 4 instead of 10 because the race condition.

output:
Thread_9 - count: 3
Thread_6 - count: 3
Thread_7 - count: 3
Thread_0 - count: 3
Thread_1 - count: 3
Thread_8 - count: 3
Thread_3 - count: 3
Thread_4 - count: 3
Thread_2 - count: 3
Thread_5 - count: 4
factorizer.getCount(): 4
 */

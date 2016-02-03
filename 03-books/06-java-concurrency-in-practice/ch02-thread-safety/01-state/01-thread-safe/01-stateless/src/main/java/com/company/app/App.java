package com.company.app;

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
StatelessFactorizer is, like most servlets, stateless: it has no fields and references no fields from other classes.
The transient state for a particular computation exists solely in local variables that are stored on the thread's stack
and are accessible only to the executing thread. One thread accessing a StatelessFactorizer cannot influence the result
of another thread accessing the same StatelessFactorizer; because the two threads do not share state, it is as if they
were accessing different instances. Since the actions of a thread accessing a stateless object cannot affect the
correctness of operations in other threads, stateless objects are thread-safe.
Stateless objects are always thread-safe.
 */
class StatelessFactorizer{
    public void service(Request req, Response resp){
        String name = req.getName();
        resp.setName("modified " + name);
        System.out.println(Thread.currentThread().getName() + ": " + req.getName() + ", " + resp.getName());
    }
}
public class App
{
    public static void main( String[] args )
    {
        final StatelessFactorizer factorizer = new StatelessFactorizer();
        for(int i = 0; i < 10; i++){
            new Thread("Thread_" + i){
                public void run(){
                    factorizer.service(new Request("request"), new Response("response"));
                }
            }.start();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("#done");
    }
}
/*
output:
Thread_9: request, modified request
Thread_1: request, modified request
Thread_6: request, modified request
Thread_5: request, modified request
Thread_0: request, modified request
Thread_7: request, modified request
Thread_4: request, modified request
Thread_8: request, modified request
Thread_2: request, modified request
Thread_3: request, modified request
#done
 */

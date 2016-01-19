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
    }
}
public class App
{
    public static void main( String[] args )
    {

    }
}

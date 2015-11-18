package com.company.app;

abstract class Handler{
    protected Handler successor;
    public void setSuccessor(Handler successor){
        this.successor = successor;
    }
    public abstract void HandleRequest(int request);
}
class ConcreteHandler1 extends Handler{
    @Override
    public void HandleRequest(int request) {
        if(request >= 0 && request < 10){
            System.out.printf("%s handled request %s\n", this.getClass().getSimpleName(), request);
        }
        else if(successor != null){
            successor.HandleRequest(request);
        }
    }
}
class ConcreteHandler2 extends Handler{
    @Override
    public void HandleRequest(int request) {
        if(request >= 10 && request < 20){
            System.out.printf("%s handled request %s\n", this.getClass().getSimpleName(), request);
        }
        else if(successor != null){
            successor.HandleRequest(request);
        }
    }
}
class ConcreteHandler3 extends Handler{
    @Override
    public void HandleRequest(int request) {
        if(request >= 20 && request < 30){
            System.out.printf("%s handled request %s\n", this.getClass().getSimpleName(), request);
        }
        else if(successor != null){
            successor.HandleRequest(request);
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        int[] requests = { 2, 5, 14, 22, 18, 3, 27, 20 };
        for(int request : requests){
            h1.HandleRequest(request);
        }
    }
}
/*
Definition
Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.
Chain the receiving objects and pass the request along the chain until an object handles it.

output:
ConcreteHandler1 handled request 2
ConcreteHandler1 handled request 5
ConcreteHandler2 handled request 14
ConcreteHandler3 handled request 22
ConcreteHandler2 handled request 18
ConcreteHandler1 handled request 3
ConcreteHandler3 handled request 27
ConcreteHandler3 handled request 20
 */

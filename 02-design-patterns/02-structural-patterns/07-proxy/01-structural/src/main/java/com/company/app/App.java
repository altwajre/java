package com.company.app;

abstract class Subject{
    public abstract void Request();
}
class RealSubject extends Subject{
    @Override
    public void Request() {
        System.out.println("Called RealSubject.Request()");
    }
}
class Proxy extends Subject{
    private RealSubject realSubject;
    @Override
    public void Request() {
        if(realSubject == null){
            realSubject = new RealSubject();
        }
        realSubject.Request();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Proxy proxy = new Proxy();
        proxy.Request();
    }
}
/*
Definition
Provide a surrogate or placeholder for another object to control access to it.

output:
Called RealSubject.Request()
 */

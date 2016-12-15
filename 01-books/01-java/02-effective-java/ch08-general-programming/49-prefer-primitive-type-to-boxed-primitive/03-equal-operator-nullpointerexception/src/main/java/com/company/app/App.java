package com.company.app;

/*
It throws a NullPointerException on when evaluating the expression (i == 42). The problem is that i is an Integer, not
an int, and like all object reference fields, its initial value is null. When the program evaluates the expression
(i == 42), it is comparing an Integer to an int. In nearly every case when you mix primitives and boxed primitives in a
single operation, the boxed primitive is auto-unboxed, and this case is no exception. If a null object reference is
auto-unboxed, you get a NullPointerException.
 */
public class App
{
    static Integer i;
    public static void main( String[] args )
    {
        if(i == 42){
            System.out.println("Unbelievable");
        }
    }
}
/*
output:
Exception in thread "main" java.lang.NullPointerException
	at com.company.app.App.main(App.java:8)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
package com.company.app;

// ArithmeticException
public class App
{
    public static void main( String[] args )
    {
        int num1 = 10;
        int num2 = 0;
        /*
        Since I'm dividing an integer with 0
        it should throw ArithmeticException
         */
        int res = num1 / num2;
        System.out.println(res);
    }
}
/*
output:
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at com.company.app.App.main(App.java:13)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
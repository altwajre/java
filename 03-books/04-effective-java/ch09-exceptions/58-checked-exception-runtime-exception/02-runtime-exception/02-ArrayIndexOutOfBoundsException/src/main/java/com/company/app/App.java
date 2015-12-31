package com.company.app;

// ArrayIndexOutOfBoundsException
public class App
{
    public static void main( String[] args )
    {
        int arr[] = {1,2,3,4,5};
        // My array has only 5 elements, but try to display 8th element. It should throw ArrayIndexOutOfBoundsException
        System.out.println(arr[7]);
    }
}
/*
output:
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 7
	at com.company.app.App.main(App.java:9)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
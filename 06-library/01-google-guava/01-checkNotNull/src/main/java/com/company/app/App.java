package com.company.app;

import com.google.common.base.Preconditions;

public class App
{
    static void foo(String msg){
        Preconditions.checkNotNull(msg, "The msg argument cannot be null.");
    }
    public static void main( String[] args )
    {
        foo(null);
    }
}
/*
output:
Exception in thread "main" java.lang.NullPointerException: The msg argument cannot be null.
	at com.google.common.base.Preconditions.checkNotNull(Preconditions.java:208)
	at com.company.app.App.foo(App.java:8)
	at com.company.app.App.main(App.java:12)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */

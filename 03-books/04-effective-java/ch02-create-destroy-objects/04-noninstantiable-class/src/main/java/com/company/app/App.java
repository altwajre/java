package com.company.app;

// Non-instantiable utility class
class UtilityClass{
    // Suppress default constructor for non-instantiability
    private UtilityClass(){
        throw new AssertionError();
    }
    static UtilityClass utilityClass = new UtilityClass();
    public static UtilityClass getInstance(){
        return utilityClass;
    }

}
public class App
{
    public static void main( String[] args )
    {
        UtilityClass.getInstance();
    }
}
/*
output: exception is expected because UtilityClass is not instantiable
Exception in thread "main" java.lang.AssertionError
	at com.company.app.UtilityClass.<init>(App.java:7)
	at com.company.app.UtilityClass.<clinit>(App.java:9)
	at com.company.app.App.main(App.java:19)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */
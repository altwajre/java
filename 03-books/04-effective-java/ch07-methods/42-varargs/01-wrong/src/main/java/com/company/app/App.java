package com.company.app;

// Sample uses of varargs
public class App
{
    // Simple use of varargs
    static int sum(int... args){
        int sum = 0;
        for(int arg : args){
            sum += arg;
        }
        return sum;
    }

    // The WRONG way to use varargs to pass one or more arguments!
    static int min(int... args){
        if(args.length == 0){
            throw new IllegalArgumentException("Too few arguments");
        }
        int min = args[0];
        for(int i = 1; i < args.length; i++){
            if(args[i] < min){
                min = args[i];
            }
        }
        return min;
    }

    public static void main( String[] args )
    {
        System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(min()); // a serious problem is that it fails at runtime rather than compile time.
    }
}
/*
output:
55
Exception in thread "main" java.lang.IllegalArgumentException: Too few arguments
	at com.company.app.App.min(App.java:17)
	at com.company.app.App.main(App.java:31)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */

package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
You lose type safety if you use a raw type like List, but not if you use a parameterized type like List<Object>
 */
public class App
{
    static void unsafeAdd(List list, Object o){
        list.add(o);
    }
//    static void unsafeAdd(List<Object> list, Object o){
//        list.add(o);
//    }
    public static void main( String[] args )
    {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, new Integer(42));
        String s = strings.get(0);  // Compiler-generated cast - exception occurs
    }
}
/*
output:
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	at com.company.app.App.main(App.java:18)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */

package com.company.app;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static java.lang.System.out;

public class App
{
    private static int count;
    private static synchronized void inc() {count++;}
    private static synchronized int cnt() {return count;}
    public static void main( String[] args )
    {
//        new Object().wait();
        print("java.lang.Object", "wait");

        System.out.println( "Hello World!" );
    }

    static void print(String className, String methodName){
        try{
            Class<?> c = Class.forName(className);
            Method[] allMethods = c.getDeclaredMethods();
            for(Method m : allMethods){
                if(!m.getName().equals(methodName)){
                    continue;
                }
                out.format("%s%n", m.toGenericString());
                out.format("  Modifiers:  %s%n", Modifier.toString(m.getModifiers()));
                out.format("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n",
                        m.isSynthetic(), m.isVarArgs(), m.isBridge());
                inc();
            }
            out.format("%d matching overload%s found%n", cnt(), (cnt() == 1 ? "" : "s"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

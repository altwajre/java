package com.company.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test{ }

// Program containing marker annotations
class Sample{
    @Test public static void m1(){} // Test should pass
    public static void m2(){}
    @Test public static void m3(){ // Test should fail
        throw new RuntimeException("Boom");
    }
    public static void m4(){}
    @Test public void m5(){} // INVALID use: nonstatic method
    public static void m6(){}
    @Test public static void m7(){ // Test should fail
        throw new RuntimeException("Crash");
    }
    public static void m8(){}
}

// Program to process marker annotations
public class App
{
    public static void main( String[] args ) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName("com.company.app.Sample");
        for(Method m : testClass.getDeclaredMethods()){
            if(m.isAnnotationPresent(Test.class)){
                tests++;
                try{
                    m.invoke(null);
                    passed++;
                }
                catch (InvocationTargetException wrappedExc){
                    Throwable exc = wrappedExc.getCause();
                    System.out.println(m + " failed: " + exc);
                }
                catch(Exception exc){
                    System.out.println("INVALID @Test: " + m);
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}

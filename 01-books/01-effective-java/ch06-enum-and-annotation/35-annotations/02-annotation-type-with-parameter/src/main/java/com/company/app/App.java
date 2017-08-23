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
@interface ExceptionTest{
    Class<? extends Exception> value();
}

// Program containing marker annotations
class Sample2{
    @ExceptionTest(ArithmeticException.class)
    public static void m1(){ // Test should pass
        int i = 0;
        i = i / i;
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m2() { // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }
    @ExceptionTest(ArithmeticException.class)
    public static void m3(){} // Should fail (no exception)
}

// Program to process marker annotations
public class App
{
    public static void main( String[] args ) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName("com.company.app.Sample2");
        for(Method m : testClass.getDeclaredMethods()){
            tests++;
            try{
                m.invoke(null);
                System.out.printf("Test %s failed: no exception%n", m);
            }
            catch (InvocationTargetException wrappedEx){
                Throwable exc = wrappedEx.getCause();
                Class<? extends Exception> excType = m.getAnnotation(ExceptionTest.class).value();
                if(excType.isInstance(exc)){
                    passed++;
                    System.out.println("#Pass");
                }
                else{
                    System.out.printf("Test %s failed: expected %s, got %s%n", m, excType.getName(), exc);
                }
            }
            catch (Exception exc){
                System.out.println("INVALID @Test: " + m);
            }
        }
    }
}

package com.company.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Indicates that the annotated method is a test method.
 * Use only on parameterless static methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ExceptionTest{
    Class<? extends Exception>[] value();
}

// Program containing marker annotations
class Sample2{
    @ExceptionTest({ IndexOutOfBoundsException.class, NullPointerException.class})
    public static void doublyBad(){
        List<String> list = new ArrayList<String>();

        // The spec permits this method to throw either
        // IndexOutOfBoundsExpection or NullPointerException
        list.addAll(5, null);
    }
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
                Class<? extends Exception>[] excTypes = m.getAnnotation(ExceptionTest.class).value();
                int oldPassed = passed;
                for(Class<? extends Exception> excType : excTypes){
                    if(excType.isInstance(exc)){
                        passed++;
                        System.out.println("#Pass");
                        break;
                    }
                }
                if(passed == oldPassed){
                    System.out.printf("Test %s failed, %s %n", m, exc);
                }
            }
            catch (Exception exc){
                System.out.println("INVALID @Test: " + m);
            }
        }
    }
}

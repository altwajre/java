package com.company.app;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static java.lang.System.out;

public class App
{
    private static final String fmt = "%24s: %s%n";

    // for the morbidly curious
//    <E extends RuntimeException> void genericThrow() throws E{}

    public static void main( String[] args )
    {
        print("java.lang.Class", "getConstructor");
        System.out.println( "Hello World!" );
    }

    static void print(String className, String methodName){
        try{
            String.class.getConstructors();
            Class<?> c = Class.forName(className);
            Method[] allMethods = c.getDeclaredMethods();
            for(Method m : allMethods){
                if(!m.getName().equals(methodName)){
                    continue;
                }
                out.format("* %s%n", m.toGenericString());

                out.format(fmt, "ReturnType", m.getReturnType());
                out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                Class<?>[] pType = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for(int i = 0; i < pType.length; i++){
                    out.format(fmt, "ParameterType", pType[i]);
                    out.format(fmt, "GenericParameterType", gpType[i]);
                }

                Class<?>[] xType = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for(int i = 0; i < xType.length; i++){
                    out.format(fmt, "ExceptionType", xType[i]);
                    out.format(fmt, "GenericExceptionType", gxType[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

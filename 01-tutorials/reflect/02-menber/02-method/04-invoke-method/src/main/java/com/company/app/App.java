package com.company.app;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Locale;

import static java.lang.System.out;
import static java.lang.System.err;

public class App<T>
{
    private boolean testDeet(Locale locale){
        out.format("Locale = %s, ISO Language Code = %s%n", locale.getDisplayName(), locale.getISO3Language());
        return true;
    }
    private int testFoo(Locale locale){return 0;}
    private boolean testBar() {return true;}

    public static void main( String[] args )
    {
        invokeMethod("com.company.app.App", "ja", "JP", "JP");

        System.out.println( "Hello World!" );
    }

    static void invokeMethod(String className, String language, String country, String variant){
        try{
            Class<?> c = Class.forName(className);
            Object t = c.newInstance();

            Method[] allMethods = c.getDeclaredMethods();
            for(Method m : allMethods){
                String mName = m.getName();
                if(!mName.startsWith("test") || (m.getGenericReturnType() != boolean.class)){
                    continue;
                }
                Type[] pType = m.getGenericParameterTypes();
                if((pType.length != 1) || Locale.class.isAssignableFrom(pType[0].getClass())){
                    continue;
                }

                out.format("#invoking %s()%n", mName);
                try{
                    m.setAccessible(true);
                    Object o = m.invoke(t, new Locale(language, country, variant));
                    out.format("  %s() returned %b%n", mName, (Boolean) o);
                }
                catch (Exception x){
                    Throwable cause = x.getCause();
                    err.format("invocation of %s failed: %s%n", mName, cause.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.company.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

import static java.lang.System.out;

public class App
{
    public static void main( String[] args )
    {
        findConstructor("java.util.Formatter", "java.util.Locale");
        System.out.println( "Hello World!" );
    }

    static void findConstructor(String className, String argName){
        try{
            Class<?> cArg = Class.forName(argName);
            Class<?> c = Class.forName(className);

            Constructor[] allConstructors = c.getDeclaredConstructors();
            for(Constructor ctor : allConstructors){
                Class<?>[] pType = ctor.getParameterTypes();
                for(int i = 0; i < pType.length; i++){
                    if(pType[i].equals(cArg)){
                        out.format("%s%n", ctor.toGenericString());
                        Type[] gpType = ctor.getGenericParameterTypes();
                        for(int j = 0; j < gpType.length; j++){
                            char ch = (pType[j].equals(cArg) ? '*' : ' ');
                            out.format("%7c%s[%d]: %s%n", ch, "GenericParameterType", j, gpType[j]);
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

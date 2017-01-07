package com.company.app;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static java.lang.System.out;

public class App
{
    volatile int share;
    int instance;
    class Inner{}

    public static void main( String[] args )
    {
        print("com.company.app.App", "volatile");
        print("com.company.app.Spy", "public");
        print("com.company.app.App$Inner", "final");
        System.out.println("Hello World!");
    }

    static void print(String className, String... args){
        try{
            Class<?> c = Class.forName(className);
            int searchMods = 0x0;
            for(int i = 0; i < args.length; i++){
                searchMods |= modifierFromString(args[i]);
            }

            Field[] flds = c.getDeclaredFields();
            out.format("#Fields in Class '%s' containing modifiers:  %s%n",
                    c.getName(),
                    Modifier.toString(searchMods));

            out.format("  Modifier: %s, searchMods: %s%n", args[0], searchMods);

            boolean found = false;
            for(Field f: flds){
                out.format("  Field: %s%n", f);
                int foundMods = f.getModifiers();
                out.format("  foundMods: %s, searchMods: %s, foundMods & searchMods: %s%n",
                        foundMods, searchMods, foundMods & searchMods);
                // Require all of the requested modifiers to be present
                if((foundMods & searchMods) == searchMods){
                    out.format("  %-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
                            f.getName(), f.isSynthetic(),
                            f.isEnumConstant());
                    found = true;
                }
            }
            if(!found){
                out.format("  No matching fields%n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int modifierFromString(String s){
        int m = 0x0;
        if ("public".equals(s))           m |= Modifier.PUBLIC;
        else if ("protected".equals(s))   m |= Modifier.PROTECTED;
        else if ("private".equals(s))     m |= Modifier.PRIVATE;
        else if ("static".equals(s))      m |= Modifier.STATIC;
        else if ("final".equals(s))       m |= Modifier.FINAL;
        else if ("transient".equals(s))   m |= Modifier.TRANSIENT;
        else if ("volatile".equals(s))    m |= Modifier.VOLATILE;
        return m;
    }
}

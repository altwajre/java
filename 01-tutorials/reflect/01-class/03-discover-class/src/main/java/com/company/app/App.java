package com.company.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import static java.lang.System.out;

public class App
{
    public static void main( String[] args )
    {
        discoverClass("java.lang.ClassCastException", ClassMember.CONSTRUCTOR);
        discoverClass("java.nio.channels.ReadableByteChannel", ClassMember.METHOD);
        discoverClass("java.nio.channels.ReadableByteChannel", ClassMember.CLASS);
        discoverClass("com.company.app.ClassMember", ClassMember.FIELD);
        discoverClass("com.company.app.ClassMember", ClassMember.METHOD);

        System.out.println( "Hello World!" );
    }

    private static void discoverClass(String arg, ClassMember classMember){
        try{
            Class<?> c = Class.forName(arg);
            out.format("Class:%n  %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            out.format("Package:%n  %s%n%n",
                    (p != null ? p.getName(): "-- No Package --"));

            switch (classMember){
                case CONSTRUCTOR:
                    printMembers(c.getConstructors(), "Constructors");
                    break;
                case FIELD:
                    printMembers(c.getFields(), "Fields");
                    break;
                case METHOD:
                    printMembers(c.getMethods(), "Methods");
                    break;
                case CLASS:
                    printClasses(c);
                    break;
                case ALL:
                    printMembers(c.getConstructors(), "Constructors");
                    printMembers(c.getFields(), "Fields");
                    printMembers(c.getMethods(), "Methods");
                    printClasses(c);
                    break;
                default:
                    assert false;

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printMembers(Member[] mbrs, String s){
        out.format("%s:%n", s);
        for(Member mbr : mbrs){
            if(mbr instanceof Field){
                out.format("  Field - %s%n", ((Field)mbr).toGenericString());
            }
            else if(mbr instanceof Constructor){
                out.format("  Constructor - %s%n", ((Constructor)mbr).toGenericString());
            }
            else if(mbr instanceof Method){
                out.format("  Method - %s%n", ((Method)mbr).toGenericString());
            }
        }
        if(mbrs.length == 0){
            out.format("  -- No %s -- %n", s);
        }
        out.format("%n");
    }

    private static void printClasses(Class<?> c){
        out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for(Class<?> cls : clss){
            out.format("  %s%n", cls.getCanonicalName());
        }
        if(clss.length == 0){
            out.format("  -- No member interfaces, classes, or enums --%n");
        }
        out.format("%n");
    }
}

package com.company.app;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static java.lang.System.out;

public class App
{
    static void testConcurrentNavigableMap(){
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");
        ConcurrentNavigableMap headMap = map.headMap("3");
        System.out.println(headMap);

    }
    public static void main( String[] args )
    {
        testConcurrentNavigableMap();
        examClass("java.util.concurrent.ConcurrentNavigableMap");
        examClass("[Ljava.lang.String;");
        examClass("java.security.Identity");
        System.out.println( "Hello World!" );
    }

    private static void examClass(String arg) {
        try{
            Class<?> c = Class.forName(arg);
            out.format("Class:%n  %s%n%n", c.getCanonicalName());
            out.format("Modifiers:%n  %s%n%n", Modifier.toString(c.getModifiers()));
            out.format("Type Parameters:%n");
            TypeVariable[] typeVariables = c.getTypeParameters();
            if(typeVariables.length != 0){
                out.format("  ");
                for(TypeVariable t : typeVariables){
                    out.format("%s ", t.getName());
                }
                out.format("%n%n");
            }
            else{
                out.format(" -- No Type Parameters --%n%n");
            }

            out.format("Implemented Interfaces %n");
            Type[] intfs = c.getGenericInterfaces();
            if(intfs.length != 0){
                for(Type intf : intfs){
                    out.format("  %s%n", intf.toString());
                }
                out.format("%n");
            }
            else{
                out.format("  -- No Super Classes -- %n%n");
            }

            out.format("Inheritance Path:%n");
            List<Class> l = new ArrayList<Class>();
            printAncestor(c, l);
            if(l.size() != 0){
                for(Class<?> cl : l){
                    out.format("  %s%n", cl.getCanonicalName());
                }
                out.format("%n");
            }
            else{
                out.format("  -- No Super Classes -- %n%n");
            }

            out.format("Annotations:%n");
            Annotation[] ann = c.getAnnotations();
            if(ann.length != 0){
                for(Annotation a : ann){
                    out.format("  $s$n", a.toString());
                }
                out.format("%n");
            }
            else{
                out.format("  -- No Annotations --%n%n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printAncestor(Class<?> c, List<Class> l){
        Class<?> ancestor = c.getSuperclass();
        if(ancestor != null){
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}

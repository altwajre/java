package com.company.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import static java.lang.System.out;

public class App
{
    private static final String  fmt = "%24s: %s%n";

    public static void main( String[] args ) throws Exception {
        printClassConstructors(App.class);
        System.out.println("=============\n");
        printClassMethods(App.class);

        System.out.println( "Hello World!" );
    }

    public static void printClassConstructors(Class c) throws Exception {
        Constructor[] allConstrutors = c.getConstructors();
        out.format(fmt, "Number of constructors", allConstrutors.length);
        for(Constructor constructor : allConstrutors){
            printConstructor(constructor);
        }
        Constructor[] allDecConst = c.getDeclaredConstructors();
        out.format(fmt, "Number of declared constructors", allDecConst.length);
        for(Constructor constructor : allDecConst){
            printConstructor(constructor);
        }
    }

    public static void printClassMethods(Class c){
        Method[] allMethods = c.getDeclaredMethods();
        out.format(fmt, "Number of methods", allMethods.length);
        for(Method m : allMethods){
            printMethod(m);
        }
    }

    public static void printConstructor(Constructor c){
        out.format("%s%n", c.toGenericString());
        Parameter[] params = c.getParameters();
        out.format(fmt, "Number of parameters", params.length);
        for(int i = 0; i < params.length; i++){
            printParameter(params[i]);
        }
    }

    public static void printMethod(Method m){
        out.format("%s%n", m.toGenericString());
        out.format(fmt, "Return type", m.getReturnType());
        out.format(fmt, "Generic return type", m.getGenericReturnType());

        Parameter[] params = m.getParameters();
        for(int i = 0; i < params.length; i++){
            printParameter(params[i]);
        }
    }

    public static void printParameter(Parameter p){
        out.format(fmt, "Parameter class", p.getType());
        out.format(fmt, "Parameter name", p.getName());
        out.format(fmt, "Modifiers", p.getModifiers());
        out.format(fmt, "Is implicit?", p.isImplicit());
        out.format(fmt, "Is name present?", p.isNamePresent());
        out.format(fmt, "Is synthetic?", p.isSynthetic());
    }
}

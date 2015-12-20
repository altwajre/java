package com.company.app;

import java.util.Set;

/*
For unknown type
 */
public class App
{
    // Use of raw type for unknown element type - DO NOT do this!
    // This method works but it uses raw types, which are dangerous.
//    static int numElementsInCommon(Set s1, Set s2){
//        int result = 0;
//        for(Object o1 : s1){
//            if(s2.contains(o1)){
//                result++;
//            }
//        }
//        return result;
//    }

    /*
    Use a safe alternative known as unbounded wildcard types. If you want to use a generic type but you don't know or care
    what the actual type parameter is, you can use a question mark instead; unbounded wildcard type for Set<E> is Set<?>.
    It is the most general parameterized Set type, capable of holding any set.
    The wildcard type is safe and the raw type isn't. raw type takes any type that is easily corrupting the collection's
    type invariant; you can't put any element (other than null) into a Collection<?>.
     */
    static int numElementsInCommon(Set<?> s1, Set<?> s2){
        int result = 0;
        for(Object o1 : s1){
            if(s2.contains(o1)){
                result++;
            }
        }
        return result;
    }
    public static void main( String[] args )
    {
        System.out.println( "Use unbounded wildcard type for unknown type!" );
    }
}

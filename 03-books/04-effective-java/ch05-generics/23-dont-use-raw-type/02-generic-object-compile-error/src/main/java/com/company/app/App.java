package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
Compile error occurs
While you can pass List<String> to a parameter of type List, you can't pass it to a paramter of type List<Object>.
There are subtyping rules for generics, and List<String> is subtype of the raw type List, but not of the parameterized
type List<Object>
As a consequence, you lose type safety if you use a raw type like List, but not if you use a parameterized type like
List<Object>
 */
public class App
{
    static void unsafeAdd(List<Object> list, Object o){
        list.add(o);
    }
    public static void main( String[] args )
    {
        List<String> strings = new ArrayList<String>();
        unsafeAdd(strings, new Integer(42));
        String s = strings.get(0);  // Compiler-generated cast - exception occurs
    }
}

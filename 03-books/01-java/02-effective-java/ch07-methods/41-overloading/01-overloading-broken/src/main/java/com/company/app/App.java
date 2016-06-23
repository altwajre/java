package com.company.app;

import java.math.BigInteger;
import java.util.*;

/*
Broken! - What does this program print?

You might expect this program to print Set, followed by List and Unknown Collection, but it doesn’t. It prints Unknown
Collection three times. Why does this happen? Because the classify method is overloaded, and the choice of which
overloading to invoke is made at compile time. For all three iterations of the loop, the compile-time type of the
parameter is the same: Collection<?>. The runtime type is different in each iteration, but this does not affect the
choice of overloading. Because the compile-time type of the parameter is Collection<?>, the only applicable overloading
is the third one, classify(Collection<?>), and this overloading is invoked in each iteration of the loop.

The behavior of this program is counterintuitive because selection among overloaded methods is static, while selection
among overridden methods is dynamic.
*/
public class App
{
    public static String classify(Set<?> s){
        return "Set";
    }
    public static String classify(List<?> lst){
        return "List";
    }
    public static String classify(Collection<?> c){
        return "Unknown Collection";
    }
    public static void main( String[] args )
    {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };
        for(Collection<?> c : collections){
            System.out.println(classify(c));
        }
    }
}
/*
output:
Unknown Collection
Unknown Collection
Unknown Collection
 */

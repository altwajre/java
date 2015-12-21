package com.company.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
With this generic static factory method, you can replace the repetitious declaration above above with this concise one.
You don't need to specify the value of the type parameter explicitly as you must when invoking generic constructors.
The compiler figures out the value of the type parameters by examining the types of the method arguments. In the case
of the program above, the compiler sees that both arguments to union are of type Set<String>, so it knows that the
type parameter E must be String. This process is called type inference.
 */
public class App
{
    // Generic static factory method
    public static <K, V> HashMap<K, V> newHashMap(){
        return new HashMap<K, V>();
    }
    public static void main( String[] args )
    {
        paramTypeCreationWithCtor();
        genericStaticFactory();
        emptyTypeParamCtor();
    }

    private static void emptyTypeParamCtor() {
        System.out.println("emptyTypeParamCtor - GOOD");
        // In Java SE 7, we can substitute parameterized type of constructor with an empty set of type parameter (<>)
        Map<String, List<String>> anagrams = new HashMap<>();
        System.out.println(anagrams.getClass().getSimpleName());
    }

    private static void genericStaticFactory() {
        System.out.println("genericStaticFactory");
        Map<String, List<String>> anagrams = newHashMap();
        System.out.println(anagrams.getClass().getSimpleName());
    }

    private static void paramTypeCreationWithCtor() {
        System.out.println("paramTypeCreationWithCtor");
        // Parameterized type instance creation with constructor
        Map<String, List<String>> anagrams = new HashMap<String, List<String>>();
        System.out.println(anagrams.getClass().getSimpleName());
    }
}
/*
output:
paramTypeCreationWithCtor
HashMap
genericStaticFactory
HashMap
emptyTypeParamCtor - GOOD
HashMap
 */

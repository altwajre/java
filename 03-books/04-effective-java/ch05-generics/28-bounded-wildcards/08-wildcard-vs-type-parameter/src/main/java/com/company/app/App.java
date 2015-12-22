package com.company.app;

import java.util.Arrays;
import java.util.List;

/*
In public API, wildcard (<?> is unbound wildcard) is better than type parameter because it's simpler. You pass a
list-any-list-and the method swaps the indexed elements. There are no type parameter to worry about. As a rule, if a
type parameter appear only once in a method declaration, replace it with a wildcard. If it's an unbounded type parameter,
replace it with an unbounded wildcard; if it's a bounded type parameter, replace it with a bounded wildcard.
 */
public class App
{
    public static void swap(List<?> list, int i, int j){
        /*
        wildcard problem: the straightforward implementation won't compile
        Compile error:
        Error:(9, 41) java: incompatible types: java.lang.Object cannot be converted to capture#1 of ?
         */
//        list.set(i, list.set(j, list.get(i)));

        /*
        The problem is that the type of list is List<?>, and you can't put any value except null into a List<?>.
        To solve the problem, write a private helper method to capture the wildcard type. The swapHelper() method
        knows that list is a List<E>. Therefore, any value it gets out of list is of type E, and that it's safe to put
        any value of type E into the list.
         */
        swapHelper(list, i, j);
    }
    // private helper method for wildcard capture
    private static <E> void swapHelper(List<E> list, int i, int j){
        list.set(i, list.set(j, list.get(i)));
    }
    public static void main( String[] args )
    {
        // Swap the first and last argument and print the resulting list
        List<String> list = Arrays.asList("a", "b", "c", "z");
        swap(list, 0, list.size() - 1);
        System.out.println(list);
    }
}
/*
output:
[z, b, c, a]
 */

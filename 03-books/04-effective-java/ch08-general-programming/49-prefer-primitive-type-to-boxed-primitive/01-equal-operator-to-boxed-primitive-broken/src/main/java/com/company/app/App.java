package com.company.app;

import java.util.Comparator;

// Apply the == operator to boxed primitives almost always wrong.
public class App
{
    public static void main( String[] args )
    {
        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            public int compare(Integer first, Integer second) {
                return first < second ? -1 : (first == second ? 0 : 1);
            }
        };

        int result = naturalOrder.compare(new Integer(42), new Integer(42));
        System.out.println(result);
    }
}
/*
output:
1

note:
The first test in naturalOrder works fine. Evaluating the expression first < second causes the Integer instances
referred to by first and second to be auto-unboxed; that is, it extracts their primitive values.
The second test evaluates the expression first==second, which performs an identity comparison on the two object
references, so the comparison returns false, and the comparator will incorrectly return 1.
 */

package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/*
If appropriate interface types exist, then parameters, return values, variables, and fields should all be declared
using interface types.
If you get into the habit of using interfaces as types, you program will be much more flexible.
It is entirely appropriate to refer to an object by a class rather than an interface if no appropriate interface exists.
It is perferable to refer to relevant base class, which is typically abstract, rather than by its implementation class.
 */
public class App
{
    public static void main( String[] args )
    {
        useInterfaceAsType(); // GOOD
        useClassAsType();     // BAD
    }

    private static void useClassAsType() {
        // Bad - uses class as type!
        Vector<Integer> lists = new Vector<Integer>();
    }

    private static void useInterfaceAsType() {
        // Good - uses interface as type
        useClassAsType();

        // Switch implementation
//        List<Integer> lists = new ArrayList<Integer>();
    }
}

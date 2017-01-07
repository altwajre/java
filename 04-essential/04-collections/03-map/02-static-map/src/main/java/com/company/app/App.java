package com.company.app;

import java.util.HashMap;
import java.util.Map;

class Global{
    public static Map<Integer, String> items;
    static{
        items = new HashMap<Integer, String>();
        items.put(1, "a");
        items.put(2, "b");
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println(Global.items);

        Global.items.put(1, "aa");

        System.out.println(Global.items);

    }
}

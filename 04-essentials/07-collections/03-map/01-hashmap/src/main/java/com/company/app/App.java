package com.company.app;

import java.util.HashMap;
import java.util.Map;

class Demo
{
    public static final Map<String, String> myMap;
    static{
        myMap = new HashMap<String, String>();
        myMap.put("111","order1");
        myMap.put("222","order2");
    }
}
public class App
{
    public static void main( String[] args )
    {
        for(Map.Entry<String, String> entry : Demo.myMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.print("key=" + key);
            System.out.print(", value=" + value);
            System.out.println();
        }

        String firstOrder = Demo.myMap.get("111");
        System.out.println("get value="+firstOrder+" from key=111");
    }
}
/*
key=111, value=order1
key=222, value=order2
get value=order1 from key=111
 */

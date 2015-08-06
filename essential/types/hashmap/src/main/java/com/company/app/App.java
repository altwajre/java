package com.company.app;

import java.util.HashMap;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "Tom");
        map.put(2, "Dick");
        map.put(3, "Harry");

        // Display map keys
        System.out.println("Display map keys:");
        for(int key : map.keySet()){
            System.out.print(key + " ");
        }
        System.out.println("");
        // Display map values
        System.out.println("Display map values:");
        for(String value : map.values()){
            System.out.print(value + " ");
        }
        System.out.println("");
        // Iterate the both key and value entrySet
        System.out.println("Display map entries:");
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.print("{" + key + ":" + value + "} " );
        }
        System.out.println("");
    }
}

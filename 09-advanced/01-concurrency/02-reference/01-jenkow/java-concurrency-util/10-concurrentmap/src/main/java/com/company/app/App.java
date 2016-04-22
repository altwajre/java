package com.company.app;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
http://tutorials.jenkov.com/java-util-concurrent/concurrentmap.html
Since ConcurrentMap is an interface, you need to use one of its implementations in order to use it.
The ConcurrentHashMap is very similar to the java.util.HashTable class, except that ConcurrentHashMap offers
concurrency than HashTable does. ConcurrentHashMap does not lock the Map while you are reading from it. Additionally,
ConcurrentHashMap does not lock the entire Map when writing to it. It only locks the part of the Map that is being
written to, internally.
 */
public class App
{
    public static void main( String[] args )
    {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("key_1", "value_1");
        System.out.println(map.get("key_1"));
    }
}
/*
output:
value_1
 */
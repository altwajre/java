package com.company.app;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

// http://tutorials.jenkov.com/java-util-concurrent/concurrentnavigablemap.html
public class App
{
    public static void main( String[] args )
    {
        ConcurrentNavigableMap<String, String> map = new ConcurrentSkipListMap<>();
        map.put("1","one");
        map.put("2", "two");
        map.put("3", "three");
        map.put("4", "four");

        testHeadMap(map);
        testTailMap(map);
        testSubMap(map);
    }

    /*
    The subMap() method returns a view of the original map which contains all keys from (including), to (excluding) two
    keys given as parameters to the method.
     */
    private static void testSubMap(ConcurrentNavigableMap<String, String> map) {
        System.out.println("#testSubMap");
        ConcurrentNavigableMap<String, String> subMap = map.subMap("2", "4");
        for(Map.Entry<String, String> entry : subMap.entrySet()){
            System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
        }
    }

    /*
    The tailMap(T fromKey) method returns a view of the map containing the keys which are greater than or equal to the
    given fromKey.
    If you make changes to the original map, these changes are reflected in the tail map.
     */
    private static void testTailMap(ConcurrentNavigableMap<String, String> map) {
        System.out.println("#testTailMap");
        ConcurrentNavigableMap<String, String> tailMap = map.tailMap("2");
        for(Map.Entry<String, String> entry : tailMap.entrySet()){
            System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
        }
    }

    /*
    The headMap(T toKey) method returns a view of the map containing the keys which are strictly less than the given key.
    If you make changes to the original map, these changes are reflected in the head map.
     */
    private static void testHeadMap(ConcurrentNavigableMap<String, String> map) {
        System.out.println("#testHeadMap");
        ConcurrentNavigableMap<String, String> headMap = map.headMap("3");
        for(Map.Entry<String, String> entry : headMap.entrySet()){
            System.out.println("key=" + entry.getKey() + ", value=" + entry.getValue());
        }
    }
}
/*
output:
#testHeadMap
key=1, value=one
key=2, value=two
#testTailMap
key=2, value=two
key=3, value=three
key=4, value=four
#testSubMap
key=2, value=two
key=3, value=three
 */
package com.company.app;

import java.util.Map;

// Skeletal Implementation
abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V>{
    // Primitive operations
    public abstract K getKey();
    public abstract V getValue();
    // Entries in modifiable maps must override this method
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }
    // Implements the general contract of Map.Entry.equals
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?, ?> arg = (Map.Entry) o;
        return equals(getKey(), arg.getKey())
                && equals(getValue(), arg.getValue());
    }
    private static boolean equals(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }
    // Implements the general contract of Map.Entry.hashCode
    @Override
    public int hashCode() {
        return hashCode(getKey()) ^ hashCode(getValue());
    }
    private static int hashCode(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println(new AbstractMapEntry<Integer, Integer>(){
            @Override
            public Integer getKey() {
                return 1;
            }
            @Override
            public Integer getValue() {
                return 2;
            }
        }.getKey());
    }
}
/*
output:
1
 */

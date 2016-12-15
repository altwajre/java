package com.company.app;

import java.util.HashMap;
import java.util.Map;

class Favorites{
    // Typesafe heterogeneous container pattern - implementation
    // Class<T> - the type tokenis unbounded.
    // String.class is Class<String> and Integer.class is Class<Integer>
    private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();
    public <T> void putFavorite(Class<T> type, T instance){
        if(type == null){
            throw new NullPointerException("Type is null");
        }
        favorites.put(type, instance);
    }
    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
    }
}

// Typesafe heterogeneous container
public class App
{
    // Typesafe heterogeneous container pattern - client
    public static void main( String[] args )
    {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);
        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
/*
output:
Java cafebabe com.company.app.Favorites
 */

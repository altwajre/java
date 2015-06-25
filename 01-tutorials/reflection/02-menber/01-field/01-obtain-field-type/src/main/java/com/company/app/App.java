package com.company.app;

import java.lang.reflect.Field;
import java.util.List;

import static java.lang.System.out;

public class App<T>
{
    public boolean[][] b = {{false, false}, {true, true}};
    public String name = "Alice";
    public List<Integer> list;
    public T val;
    public static void main( String[] args )
    {
        print("b");
        print("name");
        print("list");
        print("val");
    }

    static void print(String fieldName){
        try{
            Class<?> c = Class.forName("com.company.app.App");
            Field f = c.getField(fieldName);
            out.format("Type: %s%n", f.getType());
            out.format("GenericType: %s%n", f.getGenericType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

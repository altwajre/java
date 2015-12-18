package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
set.remove(i) selects the overloading Set.remove(Object o)
list.remove(i) selects the overloading List.remove(int index)
remove((Integer) i) selects the overloading List.remove(Object o);
 */
public class App
{
    public static void main( String[] args )
    {
        Set<Integer> set = new TreeSet<Integer>();
        List<Integer> list = new ArrayList<Integer>();

        for(int i = -3; i < 3; i++){
            set.add(i);
            list.add(i);
        }
        System.out.println(set + " " + list);

        for(int i = 0; i < 3; i++){
            set.remove(i);
            list.remove(i);
//            list.remove((Integer) i);
        }
        System.out.println(set + " " + list);
    }
}
/*
output: set.remove(i) removes object, list.remove(i) removes index
[-3, -2, -1, 0, 1, 2] [-3, -2, -1, 0, 1, 2]
[-3, -2, -1] [-2, 0, 2]
 */

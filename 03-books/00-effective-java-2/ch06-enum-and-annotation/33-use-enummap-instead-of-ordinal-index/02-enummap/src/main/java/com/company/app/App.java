package com.company.app;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Using an EnumMap to associate data with an enum
// Simplistic class representing a culinary herb
enum Type{
    ANNUAL, PERENNIAL, BIENNIAL
}
class Herb{
    private final String name;
    public final Type type;
    Herb(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    @Override
    public String toString(){
        return name;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Herb[] garden = { new Herb("Basil", Type.ANNUAL),
                new Herb("Carroway", Type.BIENNIAL),
                new Herb("Dill", Type.ANNUAL),
                new Herb("Lavendar", Type.PERENNIAL),
                new Herb("Parsley", Type.BIENNIAL),
                new Herb("Rosemary", Type.PERENNIAL) };
        // Using an EnumMap to associate data with an enum
        Map<Type, Set<Herb>> herbsByType = new EnumMap<Type, Set<Herb>>(Type.class);
        for(Type t : Type.values()){
            herbsByType.put(t, new HashSet<Herb>());
        }
        for(Herb h : garden){
            herbsByType.get(h.type).add(h);
        }
        System.out.println(herbsByType);
    }
}
/*
list these plants organized by type
output:
{ANNUAL=[Basil, Dill], PERENNIAL=[Lavendar, Rosemary], BIENNIAL=[Parsley, Carroway]}
 */

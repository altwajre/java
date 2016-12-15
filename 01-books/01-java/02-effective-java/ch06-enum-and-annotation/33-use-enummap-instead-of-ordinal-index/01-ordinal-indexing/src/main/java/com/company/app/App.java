package com.company.app;

import java.util.HashSet;
import java.util.Set;

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
        Set<Herb>[] herbsByType = (Set<Herb>[])new Set[Type.values().length];
        for(int i = 0; i < herbsByType.length; i++){
            herbsByType[i] = new HashSet<Herb>();
        }
        for(Herb h : garden){
            herbsByType[h.type.ordinal()].add(h);
        }
        // Print the results
        for(int i = 0; i < herbsByType.length; i++){
            System.out.printf("%s: %s%n", Type.values()[i], herbsByType[i]);
        }
    }
}
/*
list these plants organized by type
output:
ANNUAL: [Basil, Dill]
PERENNIAL: [Rosemary, Lavendar]
BIENNIAL: [Parsley, Carroway]
 */

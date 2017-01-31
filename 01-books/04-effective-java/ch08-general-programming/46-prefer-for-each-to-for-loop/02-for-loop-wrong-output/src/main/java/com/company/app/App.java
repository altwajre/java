package com.company.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/*
It prints nly the six "doubles" instead of the expected thirty-six combinations.
 */
enum Face{
    ONE, TWO, THREE, FOUR, FIVE, SIX
}
public class App
{
    public static void main( String[] args )
    {
        Collection<Face> faces = Arrays.asList(Face.values());
        for(Iterator<Face> i = faces.iterator(); i.hasNext();){
            for(Iterator<Face> j = faces.iterator(); j.hasNext();){
                System.out.println(i.next() + " " + j.next());
            }
        }
    }
}
/*
output:
ONE ONE
TWO TWO
THREE THREE
FOUR FOUR
FIVE FIVE
SIX SIX
 */

package com.company.app;

import java.util.Date;

// Broken "immutable" time period class
final class Period{
    private final Date start;
    private final Date end;
    Period(Date start, Date end) {
        if(start.compareTo(end) > 0){
            throw new IllegalArgumentException(start + " after " + end);
        }
        this.start = start;
        this.end = end;
    }
    public Date start(){
        return start;
    }
    public Date end(){
        return end;
    }
    public String toString(){
        return start + " - " + end;
    }
}
public class App
{
    // Two attacks on the internals of an "immutable" period
    public static void main( String[] args )
    {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78); // Modifies internals of p because Date is mutable (reference type)
        System.out.println(p);

        // Second attack on the internals of a Period instance
        start = new Date();
        end = new Date();
        p = new Period(start, end);
        p.end().setYear(78); // Modifies internals of p Date is mutable (reference type)
        System.out.println(p);
    }
}
/*
output: end date year is modified to 1978.
Wed Dec 16 23:54:32 PST 2015 - Sat Dec 16 23:54:32 PST 1978
Wed Dec 16 23:54:32 PST 2015 - Sat Dec 16 23:54:32 PST 1978
 */

package com.company.app;

import java.util.Date;

/*
If a class has mutable components that it gets from or returns to its clients, the class must defensively copy these
components. If the cost of the copy would be prohibitive and the class trusts its clients not to modify the components
inappropriately, then the defensive copy may be replaced by documentation outlining the client’s responsibility not to
modify the affected components.
 */
final class Period{
    private final Date start;
    private final Date end;
    Period(Date start, Date end) {
        this.start = new Date(start.getTime()); // defensive copy
        this.end = new Date(end.getTime()); // defensive copy
        if(start.compareTo(end) > 0){
            throw new IllegalArgumentException(start + " after " + end);
        }
    }
    public Date start(){
        return new Date(start.getTime());
    }
    public Date end(){
        return new Date(end.getTime());
    }
    public String toString(){
        return start + " - " + end;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78); // Fail to modify internals of p due to defensive copy
        System.out.println(p);

        // Second attack on the internals of a Period instance
        start = new Date();
        end = new Date();
        p = new Period(start, end);
        p.end().setYear(78); // Fail to modify internals of p due to defensive copy
        System.out.println(p);
    }
}
/*
output:
Thu Dec 17 00:03:16 PST 2015 - Thu Dec 17 00:03:16 PST 2015
Thu Dec 17 00:03:16 PST 2015 - Thu Dec 17 00:03:16 PST 2015
 */

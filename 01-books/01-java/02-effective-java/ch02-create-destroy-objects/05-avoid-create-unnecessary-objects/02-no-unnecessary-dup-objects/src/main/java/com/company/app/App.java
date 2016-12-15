package com.company.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// Doesn't create unnecessary duplicate objects
class Person{
    private final Date birthDate;
    public Person(Date birthDate) {
        // Defensive copy
        this.birthDate = new Date(birthDate.getTime());
    }
    // Other fields, methods
    // The starting and ending dates of the baby boom.
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    public boolean isBabyBoomer(){
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
public class App
{
    public static void main( String[] args ) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "31-08-1982 10:20:56";
        Person tom = new Person(sdf.parse(dateInString));
        System.out.println("Tom isBabyBoomer=" + tom.isBabyBoomer());
    }
}
/*
output:
Tom isBabyBoomer=false
 */

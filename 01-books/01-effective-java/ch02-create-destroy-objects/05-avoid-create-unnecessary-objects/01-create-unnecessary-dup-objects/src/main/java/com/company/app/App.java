package com.company.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

// Create lots of unnecessary duplicate objects
class Person{
    private final Date birthDate;
    public Person(Date birthDate){
        // Defensive copy
        this.birthDate = new Date(birthDate.getTime());
    }
    // Other fields, methods omitted
    // DON"T DO THIS!
    public boolean isBabyBoomer(){
        // Unnecessary allocation of expensive object
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
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

package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        testEnum(Day.MONDAY);
        testEnum(Day.WEDNESDAY);
        testEnum(Day.FRIDAY);
        testEnum(Day.SATURDAY);
        testEnum(Day.SUNDAY);
    }

    static void testEnum(Day day){
        switch (day){
            case MONDAY:
                System.out.println("Mondays are bad");
                break;
            case FRIDAY:
                System.out.println("Fridays are better");
                break;
            case SATURDAY: case SUNDAY:
                System.out.println("Weekends are the best");
                break;
            default:
                System.out.println("Minweek days are so-so");
                break;
        }
    }
}

package com.company.app;

import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.util.Date;

public class App
{
    public static void main( String[] args )
    {
        Instant createDate = Instant.parse("2017-02-08");
        Date date = new DateTime(createDate).toDate();
        System.out.println(date);
        // TODO: convert to date to UTC
    }
}

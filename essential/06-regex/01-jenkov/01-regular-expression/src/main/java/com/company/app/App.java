package com.company.app;

import java.util.regex.Pattern;

public class App
{
    public static void main( String[] args )
    {
        String text = "This is the text to be searched " +
                "for occurrences of the http:// pattern.";

        String pattern = ".*http://.*";

        boolean matches = Pattern.matches(pattern, text);

        System.out.println("matches = " + matches);
    }
}

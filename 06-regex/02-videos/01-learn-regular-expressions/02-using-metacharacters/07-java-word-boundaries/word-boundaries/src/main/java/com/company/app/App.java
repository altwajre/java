package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App
{
    public static void main( String[] args )
    {
        test1();

        test2();

    }

    private static void test2() {
        String text    =
                "John='abc' writes about this, and Joh writes about that," +
                        " and John writes about everything. "
                ;

        String patternString1 = "(John)(.*)";

        Pattern pattern = Pattern.compile(patternString1);
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            System.out.println("found: " + matcher.group(1));
        }
    }

    private static void test1() {
        String text = "cat hello value='tom' cat hi";
        Pattern pattern = Pattern.compile("\\s+value='([^']+)");
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            System.out.println(matcher.group(1));
            System.out.println(matcher.group());
        }
        else{
            System.out.println("Match not found.");
        }
    }
}

package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        String regex = "\\s+order_id=\"([^\"]+)";
//        String regex = "\\s+order_id='([^']+)";
        String input = "<html><body><title order_id=\"cool\"></body></html>";
//        String regex = "\\Gdog";
//        String input = "dogdog";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        boolean found = false;
        while (matcher.find()) {
            System.out.format("I found the text $%s$ starting at index %d and ending at index %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
            System.out.println(matcher.group(1));
            found = true;
        }

        if (!found) {
            System.out.format("No match found.%n");
        }

        System.out.println("#Done");
    }
}

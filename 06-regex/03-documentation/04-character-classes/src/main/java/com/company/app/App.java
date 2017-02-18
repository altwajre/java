package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App
{
    public static void main( String[] args )
    {
        or_test();

        negation_test();

        ranges_test();

        // Unions: [0-4[6-8]]

        // Intersections: [0-9&&[345]]

        // Subtraction: [0-9&&[^345]]

    }

    private static void ranges_test() {
        System.out.println("#ranges_test");
        String regex = "[a-c]"; // [a-c] means ranges "a", "b", or "c"
        String input = "a";
        testRegex(regex, input); // output: I found the text $a$ starting at index 0 and ending at index 1.

        regex = "[a-c]"; // [a-c] means ranges "a", "b", or "c"
        input = "d";
        testRegex(regex, input); // output: No match found.
    }

    private static void negation_test() {
        System.out.println("#negation_test");
        String regex = "[^bcr]at"; // [^bcr] means NOT "b", "c", nor "r"
        String input = "bat";

        testRegex(regex, input);
    }
/*
output:
#negation_test
No match found.
 */

    private static void or_test() {
        System.out.println("#or_test");
        String regex = "[bcr]at"; // [bcr] means "b", "c", or "r"
        String input = "bat";

        testRegex(regex, input);
    }
/*
output:
#or_test
I found the text $bat$ starting at index 0 and ending at index 3.
 */

    private static void testRegex(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);

        boolean found = false;
        while (matcher.find()) {
            System.out.format("I found the text $%s$ starting at index %d and ending at index %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
//            System.out.println(matcher.group(1));
            found = true;
        }

        if (!found) {
            System.out.format("No match found.%n");
        }
    }
}

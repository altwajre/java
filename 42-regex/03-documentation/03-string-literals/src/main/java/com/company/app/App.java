package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://docs.oracle.com/javase/tutorial/essential/regex/literals.html
public class App
{
    public static void main( String[] args )
    {
        test1();

        test2();

        test3();
    }

    private static void test3() {
        System.out.println("#test3");
        String regex = "cat."; // "." means "any character", metacharacters: <([{\^-=$!|]})?*+.>
        String input = "cats";

        testRegex(regex, input);
    }
/*
output:
#test3
I found the text $cats$ starting at index 0 and ending at index 4.
 */

    private static void test2() {
        System.out.println("#test2");
        String regex = "foo";
        String input = "foofoofoo";

        testRegex(regex, input);
    }
/*
output:
#test2
I found the text $foo$ starting at index 0 and ending at index 3.
I found the text $foo$ starting at index 3 and ending at index 6.
I found the text $foo$ starting at index 6 and ending at index 9.
 */

    private static void test1() {
        System.out.println("#test1");
        String regex = "foo";
        String input = "foo";

        testRegex(regex, input);
    }
/*
output:
#test1
I found the text $foo$ starting at index 0 and ending at index 3.
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

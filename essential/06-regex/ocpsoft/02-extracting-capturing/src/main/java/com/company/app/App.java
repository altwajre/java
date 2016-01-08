package com.company.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Specific values can be selected out of a large complex body of text. These values can be used in the application
 */
public class App
{
    public static void main( String[] args )
    {
        String input = "I have a cat, but I like my dog better.";
/*
()		group everything within the parenthesis as group 1
mouse	match the text ‘mouse’
|		alternation: match any one of the sections of this group
cat		match the text ‘cat’
 */
        Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
        Matcher m = p.matcher(input);

        List<String> animals = new ArrayList<>();
        while(m.find()){
            System.out.println("Found a " + m.group() + ".");
            animals.add(m.group());
        }
    }
}
/*
output:
Found a cat.
Found a dog.
 */
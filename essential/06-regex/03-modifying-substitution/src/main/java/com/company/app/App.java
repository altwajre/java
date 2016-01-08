package com.company.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Values in text can be replaced with new values, for example, you could replace all instances of the word 'clientId=',
followed by a number, with a mask to hide the original text.
 */
public class App
{
    public static void main( String[] args )
    {
        String input = "User clientId=23421. Some more text clientId=33432. This clientNum=100";
/*
(clientId=) 	group everything within the parenthesis as group 1
clientId=	    match the text ‘clientId=’
(\\d+)		    group everything within the parenthesis as group 2
\\d+		    match one or more digits
 */
        Pattern p = Pattern.compile("(clientId=)(\\d+)");
        Matcher m = p.matcher(input);

        StringBuffer result = new StringBuffer();
        while(m.find()){
            System.out.println("Masking: " + m.group(2));
            m.appendReplacement(result, m.group(1) + "***masked***");
        }
        m.appendTail(result);
        System.out.println(result);
    }
}
/*
output:
Masking: 23421
Masking: 33432
User clientId=***masked***. Some more text clientId=***masked***. This clientNum=100
 */
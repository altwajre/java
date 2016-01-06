package com.company.app;

import com.google.common.base.Strings;

public class App
{
    public static void main( String[] args )
    {
        String string1 = "";
        String string2 = "\t\r\n";
        String string3 = " ";
        String string4 = null;
        String string5 = "Hi";

        System.out.printf("'%s' is empty? %s", string1, Strings.isNullOrEmpty(string1));
        System.out.printf("\n'%s' is not empty? %s", string1, Strings.isNullOrEmpty(string1));

        System.out.printf("\n\n'\\t\\r\\n' is empty? %s", string2, Strings.isNullOrEmpty(string2));
        System.out.printf("\n'\\t\\r\\n' is not empty? %s", string2, Strings.isNullOrEmpty(string2));

        System.out.printf("\n\n'%s' is empty? %s", string3, Strings.isNullOrEmpty(string3));
        System.out.printf("\n'%s' is not empty? %s", string3, Strings.isNullOrEmpty(string3));

        System.out.printf("\n\n'%s' is empty? %s", string4, Strings.isNullOrEmpty(string4));
        System.out.printf("\n'%s' is not empty? %s", string4, Strings.isNullOrEmpty(string4));

        System.out.printf("\n\n'%s' is empty? %s", string5, Strings.isNullOrEmpty(string5));
        System.out.printf("\n'%s' is not empty? %s", string5, Strings.isNullOrEmpty(string5));
    }
}
/*
output:
'' is empty? true
'' is not empty? true

'\t\r\n' is empty?

'\t\r\n' is not empty?


' ' is empty? false
' ' is not empty? false

'null' is empty? true
'null' is not empty? true

'Hi' is empty? false
'Hi' is not empty? false
 */

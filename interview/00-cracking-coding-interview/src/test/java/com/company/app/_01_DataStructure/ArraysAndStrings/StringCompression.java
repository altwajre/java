package com.company.app._01_DataStructure.ArraysAndStrings;

import org.junit.Test;

/*
Q: basic string compression using the counts of repeated chars.
For example, the string aabcccccaaa would become a2b1c5a3.
 */

public class StringCompression {

    @Test
    public void Test() {
        countCompressionTest();
        compressTest();
    }

    private static void countCompressionTest() {
        System.out.println("abc".charAt(0));  // a
        System.out.println(countCompression("aaaaa"));  // 2
        System.out.println(countCompression("abc"));  // 6
        System.out.println(countCompression("aabcccccaaa"));  // 8
    }
    static int countCompression(String str){
        if(str == null || str.isEmpty()) return 0;
        char current = str.charAt(0);
        int size = 0;
        int count = 1;
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == current){  // found repeated char
                count++;
            }
            else{
                size += 1 + String.valueOf(count).length();  // increase size: size = size + 1 + countLength
                current = str.charAt(i);  // store new char to current
                count = 1;
            }
        }
        size += 1 + String.valueOf(count).length();  // increase size: size = size + 1 + countLength
        return size;
    }

    private static void compressTest() {
        System.out.println(compress("aabcccccaaa"));  // a2b1c5a3
    }
    static String compress(String str){
        // check if compression would create a longer string
        int size = countCompression(str);  // get compression size
        if(size >= str.length()){
            return str;
        }
        StringBuffer strBuf = new StringBuffer();
        char current = str.charAt(0);
        int count = 1;
        for(int i = 1; i < str.length(); i++){
            if(str.charAt(i) == current){  // found repeated char
                count++;
            }
            else{  // insert char count, and update last char
                strBuf.append(current);  // insert char
                strBuf.append(count);  // insert count
                current = str.charAt(i);
                count = 1;
            }
        }
        strBuf.append(current);
        strBuf.append(count);
        return strBuf.toString();
    }
}
/*
output:
a
2
6
8
a2b1c5a3
 */

package com.company.app;

/*
Q: Implementing an algorithm to determine if a string has all unique characters.
What if you cannot use additional data structure?

A: The time complexity for this code is O(n), where n is the length of the string. The space complexity is O(1).
 */
public class App
{
    public static void main( String[] args )
    {
        isUniqueCharsTest();
        isUniqueCharsUsingBitTest();
    }
    private static void isUniqueCharsTest() {
        System.out.println(isUniqueChars("abc"));  // true
        System.out.println(isUniqueChars("abbc"));  // false
        System.out.println(isUniqueChars("~!@"));  // true
        System.out.println(isUniqueChars("~!!@"));  // false
    }
    static boolean isUniqueChars(String str){
        if(str.length() > 256) return false;  // 256 possible unique characters
        boolean[] char_set = new boolean[256];  // ASCII has 256 chars, index from 0 to 255
        for(int i = 0; i < str.length(); i++){
            int val = str.charAt(i);  // convert char to int - 'a' => 97
            if(char_set[val]){  // Already found this char in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    private static void isUniqueCharsUsingBitTest() {
        System.out.println(isUniqueCharsUsingBit("abc"));  // true
        System.out.println(isUniqueCharsUsingBit("abbc"));  // true
    }
    /*
    reduce space usage by using a bit vector.
    assume only lower case letters a through z.
     */
    static boolean isUniqueCharsUsingBit(String str){
        int checker = 0;
        for(int i = 0; i < str.length(); i++){
            int val = str.charAt(i) - 'a';  // convert lower case letter to number between 0 to 25
            // 1 << 1 = 2 (binary is 10); 1 << 2 = 4 (binary is 100)
            if((checker & (1 << val)) > 0){  // return false if checker and val > 0 (10 and 10 = 10)
                return false;
            }
            checker |= (1 << val);  // checker or val (01 or 10 = 11)
        }
        return true;
    }
}


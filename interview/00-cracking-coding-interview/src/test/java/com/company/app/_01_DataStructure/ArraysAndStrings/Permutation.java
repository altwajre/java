package com.company.app._01_DataStructure.ArraysAndStrings;

import org.junit.Test;

import java.util.Arrays;

import static java.lang.System.out;

/*
Q: Given two strings, write a method to decide if one is a permutation of the other.
Character set was ASCII. Two strings have same number of characters.

 */
public class Permutation {

    @Test
    public void Test() {
        permutationSortTest();
        permutationTest();
    }

    private static void permutationSortTest() {
        out.println("#permutationSortTest");
        out.println(permutationSort("abcd", "bdca"));  // true
        out.println(permutationSort("abcd", "bdcz"));  // false
        out.println(permutationSort("abcad", "abdca"));  // true
    }

    /*
    sort the strings and then check two strings are equal
     */
    static boolean permutationSort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }

    static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    private static void permutationTest() {
        out.println("#permutationTest");
        out.println(permutation("abcd", "bdca"));  // true
        out.println(permutation("abcd", "bdcz"));  // false
        out.println(permutation("abcad", "abdca"));  // true
    }

    /*
    check if two strings have identical char counts
     */
    static boolean permutation(String s, String t) {
        if (s == t) return true;
        if (s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[256];  // assumption
        char[] s_array = s.toCharArray();
        for (char c : s_array) {  // count number of each char in s.
            letters[c]++;  // letters[c] = letters[(int)c], letters['a'] = letters[97]
        }
        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            if (--letters[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
/*
output:
#permutationSortTest
true
false
true
#permutationTest
true
false
true
 */

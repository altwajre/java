package com.company.app;

/*
Q: use isSubstring() only once to check if s2 is a rotation of s1

 */
public class App {
    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        boolean result = isRotation(s1, s2);
        System.out.println(result);
    }

    static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        // check that s1 and s2 are equal length and not empty
        if (len == s2.length() && len > 0) {
            String s1s1 = s1 + s1;  // concatenate s1 and s1
            return s1s1.contains(s2);  // use built-in contains() instead of isSubstring()
        }
        return false;
    }
}
/*
output:
true
 */

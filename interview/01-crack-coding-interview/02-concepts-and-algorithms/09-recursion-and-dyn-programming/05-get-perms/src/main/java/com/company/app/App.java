package com.company.app;

import java.util.ArrayList;

/*
  Q: compute all permutations of a string
  permutations of abc are [abc, bac, bca, acb, cab, cba]

 */
public class App 
{
    /*
     Solution: insert nth item into every spot in each of substring n-1
       call stack:
       getPerms("abc") - first=a, words=[bc,cb], return [abc,bac,bca,acb,cab,cba]
       getPerms("bc")  - first=b, words=[c],     return [bc,cb]
       getPerms("c")   - first=c, words=[""],    return [c]
       getPerms("")    - str.len==0              return [""]

        input: getPerms("abc")
       output: [abc, bac, bca, acb, cab, cba]
     */
    static ArrayList<String> getPerms(String str){
        if(str == null) return null;
        ArrayList<String> permutations = new ArrayList<String>();
        if(str.length() == 0){ // base case
            permutations.add("");
            return permutations;
        }
        char first = str.charAt(0); // get the first character
        String remainder = str.substring(1); // remove the 1st character
        ArrayList<String> words = getPerms(remainder);
        for(String word : words){
            for(int i = 0; i <= word.length(); i++){
                String s = insertCharAt(word, first, i);
                permutations.add(s);
            }
        }
        return permutations;
    }
    static String insertCharAt(String word, char c, int i){
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }
    public static void main( String[] args )
    {
        System.out.println(getPerms("abc"));
    }
}

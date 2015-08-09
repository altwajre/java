package com.company.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/*
  Q: Sort an array of strings so that all the anagrams are next to each other.

  A: Sort each string chars and sort strings to group them

    output:
    ele apple banana carrot duck papel tarroc tarrco cudk eel lee
    banana carrot tarroc tarrco apple papel duck cudk ele eel lee
    [aaabnn, acorrt, aelpp, cdku, eel]
 */
public class App 
{
    static class AnagramComparator implements Comparator<String> {
        public TreeSet set;  // use to TreeSet to see the sorted strings
        public AnagramComparator(){
            set = new TreeSet();
        }
        public String sortChars(String s){
            char[] content = s.toCharArray();
            Arrays.sort(content);
            return new String(content);
        }
        public int compare(String s1, String s2){
            String t1 = sortChars(s1);
            String t2 = sortChars(s2);
            set.add(t1);
            set.add(t2);

            int result = t1.compareTo(t2);
            /*
              zero when s1 == s2
              positive when s1 > s2
              otherwise, negative
             */
            return result;
        }
    }
    static String arrayToString(String[] array){
        StringBuilder sb = new StringBuilder();
        for(String v : array){
            sb.append(v + " ");
        }
        return sb.toString();
    }
    public static void main(String[] args )
    {
        String[] array = {"ele", "apple", "banana", "carrot", "duck", "papel", "tarroc", "tarrco", "cudk", "eel", "lee"};
        System.out.println(arrayToString(array));
        AnagramComparator comparator = new AnagramComparator();
        Arrays.sort(array, comparator);
        System.out.println(arrayToString(array));
        System.out.println(comparator.set);

    }
}

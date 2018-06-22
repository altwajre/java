package com.company.app.ConceptsAndAlgorithms.SortAndSearch;

import org.junit.Test;

import java.util.*;

/*
https://en.wikipedia.org/wiki/Anagram
An anagram is word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

  Q: Sort an array of strings so that all the anagrams are next to each other.

  A: Sort each string chars and sort strings to group them

    output:
    ele apple banana carrot duck papel tarroc tarrco cudk eel lee
    banana carrot tarroc tarrco apple papel duck cudk ele eel lee
    [aaabnn, acorrt, aelpp, cdku, eel]
 */
public class GroupStringsInAnagrams {

    @Test
    public void Test() {
        useComparatorToSortSortedStrings();
        useMapToGroupSortedStrings();
    }

    /*
      Solution 1:
      Use Comparator to sort sorted strings

     */
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

    /*
      Solution 2:
      Use map to group sorted strings

     */
    static void sort(String[] array){
        Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        // Group words by anagram
        for(String s : array){
            String key = sortChars(s);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<String>());
            }
            ArrayList<String> anagrams = map.get(key);
            anagrams.add(s);
        }
        // Convert hash table to array
        int index = 0;
        for(String key : map.keySet()){
            ArrayList<String> list = map.get(key);
            for(String t : list){
                array[index] = t;
                index++;
            }
        }
    }
    static String sortChars(String s){
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
    private static void useComparatorToSortSortedStrings() {
        System.out.println("#useComparatorToSortSortedStrings");
        String[] array = {"ele", "apple", "banana", "carrot", "duck", "papel", "tarroc", "tarrco", "cudk", "eel", "lee"};
        System.out.println(arrayToString(array));
        AnagramComparator comparator = new AnagramComparator();
        Arrays.sort(array, comparator);
        System.out.println(arrayToString(array));
        System.out.println(comparator.set);
    }

    private static void useMapToGroupSortedStrings() {
        System.out.println("\n#useMapToGroupSortedStrings");
        String[] array = {"ele", "apple", "banana", "carrot", "duck", "papel", "tarroc", "tarrco", "cudk", "eel", "lee"};
        System.out.println(arrayToString(array));
        sort(array);
        System.out.println(arrayToString(array));
    }
}
/*
output:
#useComparatorToSortSortedStrings
ele apple banana carrot duck papel tarroc tarrco cudk eel lee
banana carrot tarroc tarrco apple papel duck cudk ele eel lee
[aaabnn, acorrt, aelpp, cdku, eel]

#useMapToGroupSortedStrings
ele apple banana carrot duck papel tarroc tarrco cudk eel lee
banana apple papel duck cudk carrot tarroc tarrco ele eel lee
 */

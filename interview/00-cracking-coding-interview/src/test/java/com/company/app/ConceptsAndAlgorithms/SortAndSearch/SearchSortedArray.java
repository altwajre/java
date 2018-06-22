package com.company.app.ConceptsAndAlgorithms.SortAndSearch;

import org.junit.Test;

/*
 Q: find location of a given string in interspersed with empty sorted strings.
    such as {"a", "", "", "b", "", "c", ""}

 */
public class SearchSortedArray {

    @Test
    public void Test() {
        String[] stringList = {"apple", "", "", "banana", "", "", "", "carrot", "duck", "", "", "eel", "", "flower"};
        System.out.println("#search recursion");
        System.out.println(search(stringList, "ac"));
        System.out.println(search(stringList, "duck"));

        System.out.println("#search iterative");
        System.out.println(searchItera(stringList, "ac", 0, stringList.length - 1));
        System.out.println(searchItera(stringList, "duck", 0, stringList.length - 1));
    }
    /*
 Solution: iterative
 */
    static int searchItera(String[] strings, String target, int firstIndex, int lastIndex){
        while(firstIndex <= lastIndex){
            int midIndex = (lastIndex + firstIndex) / 2;
            if(strings[midIndex].isEmpty()){ // If mid is empty, find closet non-empty string
                int leftCurIndex = midIndex - 1;
                int rightCurIndex = midIndex + 1;
                while(true){
                    if(leftCurIndex < firstIndex && rightCurIndex > lastIndex) return -1;
                    else if(rightCurIndex <= lastIndex && !strings[rightCurIndex].isEmpty()){
                        midIndex = rightCurIndex;
                        break;
                    }
                    else if(leftCurIndex >= firstIndex && !strings[leftCurIndex].isEmpty()){
                        midIndex = leftCurIndex;
                        break;
                    }
                    rightCurIndex++;
                    leftCurIndex--;
                }
            }
            int result = strings[midIndex].compareTo(target);
            if(result == 0) return midIndex;
            else if(result < 0) firstIndex = midIndex + 1; // search right
            else lastIndex = midIndex - 1; // search left
        }
        return -1;
    }

    /*
     Solution: recursion
     */
    static int searchRecur(String[] strings, String target, int firstIndex, int lastIndex){
        if(firstIndex > lastIndex) return -1;
        int midIndex = (lastIndex + firstIndex) / 2;
        if(strings[midIndex].isEmpty()){ // if mid is empty, find closest non-empty string
            int leftCurIndex = midIndex - 1;
            int rightCurIndex = midIndex + 1;
            while(true){
                if(leftCurIndex < firstIndex && rightCurIndex > lastIndex) return -1;
                else if(rightCurIndex <= lastIndex && !strings[rightCurIndex].isEmpty()){
                    midIndex = rightCurIndex;
                    break;
                }
                else if(leftCurIndex >= firstIndex && !strings[leftCurIndex].isEmpty()){
                    midIndex = leftCurIndex;
                    break;
                }
                rightCurIndex++;
                leftCurIndex--;
            }
        }
        // check for string, and recurse if necessary
        if(target.equals(strings[midIndex])) return midIndex; // found it
        else if(strings[midIndex].compareTo(target) < 0) // search right if mid < target
            return searchRecur(strings, target, midIndex + 1, lastIndex);
        else return searchRecur(strings, target, firstIndex, midIndex - 1); // search left
    }
    public static int search(String[] strings, String str) {
        if (strings == null || str == null || str.isEmpty()) {
            return -1;
        }
        return searchRecur(strings, str, 0, strings.length - 1);
    }
}
/*
output:
#search recursion
-1
8
#search iterative
-1
8
 */

package com.company.app;

import java.util.*;

/*
  The set operations intersection, union, and symmetric difference
  http://www.leda-tutorial.org/en/official/ch02s08s02.html

  Differences Between HashSet, LinkedHashSet and TreeSet In Java
  http://javaconceptoftheday.com/hashset-vs-linkedhashset-vs-treeset-in-java/
 */
public class App 
{
    public static void main( String[] args )
    {
        testBasic();
        testContainsContainsAll();

        testUnion();  // addAll()
        testIntersection();  // retainAll()
        testSymmetricDifference();  // union.removeAll(intersection)

        testUnorderedHashSet();
        testOrderedLinkedHashSet();
        testSortedTreeSet();
    }

    private static void testSortedTreeSet() {
        System.out.println("#testSortedTreeSet");
        String[] items = {"c","a","f","d","b","a"};
        Set<String> set = new TreeSet<String>();
        for(String item : items){
            set.add(item);
        }
        System.out.println(set);
    }

    private static void testOrderedLinkedHashSet() {
        System.out.println("#testOrderedLinkedHashSet");
        String[] items = {"c","a","f","d","b","a"};
        Set<String> set = new LinkedHashSet<String>();
        for(String item : items){
            set.add(item);
        }
        System.out.println(set);
    }

    private static void testUnorderedHashSet() {
        System.out.println("#testUnorderedHashSet");
        // unpredictable order
        String[] items = {"c","a","f","d","b","a"};
        Set<String> set = new HashSet<String>();
        for(String item : items){
            set.add(item);
        }
        System.out.println(set);
    }

    private static void testSymmetricDifference() {
        System.out.println("#testSymmetricDifference");
        int[] numbers1 = {1,2,3,4};
        Set<Integer> set1 = new HashSet<Integer>();
        for(int n : numbers1){
            set1.add(n);
        }

        int[] numbers2 = {2,4,6,8};
        Set<Integer> set2 = new HashSet<Integer>();
        for(int n : numbers2){
            set2.add(n);
        }

        Set intersection = new HashSet(set1);
        intersection.retainAll(set2);

        Set union = new HashSet(set1);
        union.addAll(set2);

        Set symmetricDifference = new HashSet(union);
        symmetricDifference.removeAll(intersection);

        System.out.println(symmetricDifference);  // [1, 3, 6, 8]
    }

    private static void testIntersection() {
        System.out.println("#testIntersection");
        int[] numbers1 = {1,2,3,4};
        HashSet<Integer> set1 = new HashSet<Integer>();
        for(int n : numbers1){
            set1.add(n);
        }

        int[] numbers2 = {2,4,6,8};
        HashSet<Integer> set2 = new HashSet<Integer>();
        for(int n : numbers2){
            set2.add(n);
        }

        HashSet intersection = new HashSet(set1);
        intersection.retainAll(set2);

        System.out.println(intersection);  // [2, 4]
    }

    private static void testUnion() {
        System.out.println("#testUnion");
        int[] numbers1 = {1,2,3,4};
        HashSet<Integer> set1 = new HashSet<Integer>();
        for(int n : numbers1){
            set1.add(n);
        }

        int[] numbers2 = {2,4,6,8};
        HashSet<Integer> set2 = new HashSet<Integer>();
        for(int n : numbers2){
            set2.add(n);
        }

        HashSet union = new HashSet(set1);
        union.addAll(set2);

        System.out.println(union);  // [1, 2, 3, 4, 6, 8]
    }

    private static void testContainsContainsAll() {
        System.out.println("#testContainsContainsAll");
        // An ArrayList of four Strings
        ArrayList<String> list = new ArrayList<String>();
        list.add("Tom");
        list.add("Dick");
        list.add("Tom");  // NOTE: Duplicate element in list
        list.add("Harry");

        // Add all elements to HashSet.
        HashSet<String> hash = new HashSet<String>();
        hash.addAll(list);

        // Use contains
        boolean a = hash.contains("Tom");
        System.out.println("contains 'Tom': " + a);

        // Use containsAll
        boolean b = hash.containsAll(list);
        System.out.println("containsAll: " + b);
    }

    private static void testBasic() {
        System.out.println("#testBasic");
        // Create HashSet.
        HashSet<String> hash = new HashSet<String>();
        System.out.println(hash.isEmpty()); // true

        hash.add("Tom");
        hash.add("Dick");
        hash.add("Tom"); // NOTE: Duplicate element
        hash.add("Harry");

        System.out.println(hash.isEmpty()); // false
        // Display size
        System.out.println(hash.size());

        System.out.println(hash.contains("Tom"));
        System.out.println(hash.contains("Dick"));
        System.out.println(hash.contains("Harry"));
    }
}

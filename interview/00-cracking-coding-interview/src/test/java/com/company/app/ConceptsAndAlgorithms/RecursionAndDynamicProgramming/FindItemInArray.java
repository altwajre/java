package com.company.app.ConceptsAndAlgorithms.RecursionAndDynamicProgramming;

import org.junit.Test;

/*
 Q: Find index in an array A[1...n-1] is defined to be an index such that A[i] = i.
    Given a sorted array of distinct integers, write a method to find a magic index.
 */
public class FindItemInArray {

    @Test
    public void Test() {
        linearSearchTest();

        binarySearchTest();
    }

    static int[] numbers = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
    // array (-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13)
    // index [  0,   1,  2, 3, 4, 5, 6, 7, 8,  9, 10]

    // linear search
    static int linearSearch(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == i) {
                return i;
            }
        }
        return -1;
    }

    // binary search
    static int binarySearch(int[] array, int startIndex, int endIndex) {
        if (endIndex < startIndex || startIndex < 0 || endIndex >= array.length) return -1;
        int mid = (startIndex + endIndex) / 2;
        if (array[mid] == mid) return mid;
        else if (array[mid] > mid) return binarySearch(array, startIndex, mid - 1);
        else return binarySearch(array, mid + 1, endIndex);
    }

    private static void binarySearchTest() {
        System.out.println("binarySearchTest");
        System.out.println(binarySearch(numbers, 0, numbers.length - 1));
    }

    private static void linearSearchTest() {
        System.out.println("linearSearchTest");
        System.out.println(linearSearch(numbers));
    }
}
/*
output:
linearSearchTest
7
binarySearchTest
7
 */

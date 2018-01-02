package com.company.app.ConceptsAndAlgorithms.SortAndSearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
  Q: Merge two sorted arrays in sorted order.

 */
public class MergeSortedArrays {

    @Test
    public void Test() {
        int[] a = {2,4,6,8,10,-1,-1,-1,-1,-1,-1};
        int[] b = {1,3,5,7};
        int aSize = 5;
        int bSize = b.length;
        merge(a, b, aSize, bSize);
        System.out.println(Arrays.toString(a));

        System.out.println("Merge into List");
        List merge = merge(new int[]{2, 4}, new int[]{1, 3});
        merge.forEach(i -> {
            System.out.println(i);
        });

    }

    // aSize does not include buffer
    static void merge(int[] a, int[] b, int aSize, int bSize){
        int indexMerged = bSize + aSize - 1; // Index of last location of merged array
        int aLastIndex = aSize - 1; // Index of last element in array a
        int bLastIndex = bSize - 1; // Index of last element in array b

        // Merge a and b, starting from the last element in each
        while(bLastIndex >= 0){
            if(aLastIndex >= 0 && a[aLastIndex] > b[bLastIndex]){ // end of a is bigger than end of b
                a[indexMerged] = a[aLastIndex]; // copy element
                aLastIndex--;
            }
            else{
                a[indexMerged] = b[bLastIndex]; // copy element
                bLastIndex--;
            }
            indexMerged--; // move indices
        }
    }

    static List merge(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        int aLastIndex = a.length - 1;
        int bLastIndex = b.length - 1;
        while (aLastIndex >= 0 || bLastIndex >= 0) {
            if(aLastIndex < 0) {
                if(bLastIndex != -1){
                    list.add(0, b[bLastIndex]);
                    bLastIndex--;
                }
            }
            if(bLastIndex < 0) {
                if(aLastIndex != -1) {
                    list.add(0, a[aLastIndex]);
                    aLastIndex--;
                }
            }
            if(aLastIndex >=0 && bLastIndex >= 0){
                if(a[aLastIndex] > b[bLastIndex]){
                    if(aLastIndex != -1){
                        list.add(0, a[aLastIndex]);
                        aLastIndex--;
                    }
                }
                else{
                    if(bLastIndex != -1){
                        list.add(0, b[bLastIndex]);
                        bLastIndex--;
                    }
                }
            }
        }
        return list;
    }
}
/*
output:
[1, 2, 3, 4, 5, 6, 7, 8, 10, -1, -1]
Merge into List
1
2
3
4
 */

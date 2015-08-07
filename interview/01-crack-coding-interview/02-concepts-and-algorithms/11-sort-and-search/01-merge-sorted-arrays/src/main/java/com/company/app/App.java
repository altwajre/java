package com.company.app;

import java.util.Arrays;

/*
  Q: Merge two sorted arrays in sorted order.

 */
public class App 
{
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
    public static void main( String[] args )
    {
        int[] a = {2,4,6,8,10,-1,-1,-1,-1,-1,-1};
        int[] b = {1,3,5,7};
        int aSize = 5;
        int bSize = b.length;
        merge(a, b, aSize, bSize);
        System.out.println(Arrays.toString(a));
    }
}

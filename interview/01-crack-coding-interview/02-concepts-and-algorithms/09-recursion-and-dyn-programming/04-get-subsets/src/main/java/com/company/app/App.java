package com.company.app;

import java.util.ArrayList;

/*
  Q: return all subsets of a set
  For example, subsets of a set [0,1,2] are [],[2],[1],[2,1],[0],[2,0],[1,0],[2,1,0] - no repeated set
               permutations - 3! = 3 x 2 x 1 = 6

  Combinatorics - https://www.youtube.com/watch?v=3Ry7gl-LSGA
 */
public class App 
{
    /*
      Solution 1: Recursion
      call stacks:
      getSubsets(set[0,1,2], 0) - return [[],[2],[1],[2,1],[0],[2,0],[1,0],[2,1,0]]
      getSubsets(set[0,1,2], 1) - return [[],[2],[1],[2,1]]
      getSubsets(set[0,1,2], 2) - return [[],[2]]
      getSubsets(set[0,1,2], 3) - return [[]]  because set.size() == index

      output: [[], [2], [1], [2, 1], [0], [2, 0], [1, 0], [2, 1, 0]]
     */
    static ArrayList<ArrayList<Integer>> getSubsetsRecur(ArrayList<Integer> set, int index){
        ArrayList<ArrayList<Integer>> allsubsets;
        if(set.size() == index){ // Base case - add empty set
            allsubsets = new ArrayList<ArrayList<Integer>>();
            allsubsets.add(new ArrayList<Integer>());
        }
        else{
            allsubsets = getSubsetsRecur(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : allsubsets){
                ArrayList<Integer> newsubset= new ArrayList<Integer>();
                newsubset.addAll(subset);
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }
        return allsubsets;
    }

    /*
      Solution 2: Combinatorics
      Generate all binary numbers. We iterate through all numbers from 1 to 2^n and translate the binary
      representation of the numbers into a set.

      binary  integer
        00       0
        01       1
        10       2
        11       3
       100       4
       101       5
       110       6
       111       7

      input: getSubsetsFromBinary(set[0,1,2])
      output: [[], [0], [1], [0, 1], [2], [0, 2], [1, 2], [0, 1, 2]]
     */
    static ArrayList<ArrayList<Integer>> getSubsetsFromBinary(ArrayList<Integer> set){
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << set.size(); // Compute 2^n
        for(int k = 0; k < max; k++){
            ArrayList<Integer> subset = convertIntToSet(k, set);
            allsubsets.add(subset);
        }
        return allsubsets;
    }

    static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set){
        ArrayList<Integer> subset = new ArrayList<Integer>();
        int index = 0;
        for(int k = x; k > 0; k >>= 1){
            if((k & 1) == 1){
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }

    public static void main( String[] args )
    {
        int count = 3;
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < count; i++) list.add(i);

        getSubsetsRecurTest(list);
        getSubsetsFromBinaryTest(list);
    }

    private static void getSubsetsFromBinaryTest(ArrayList<Integer> list) {
        System.out.println("#getSubsetsFromBinaryTest");
        ArrayList<ArrayList<Integer>> subsets = getSubsetsFromBinary(list);
        System.out.println(subsets);
    }

    private static void getSubsetsRecurTest(ArrayList<Integer> list) {
        System.out.println("#getSubsetsRecurTest");
        ArrayList<ArrayList<Integer>> subsets = getSubsetsRecur(list, 0);
        System.out.println(subsets.toString());
    }
}

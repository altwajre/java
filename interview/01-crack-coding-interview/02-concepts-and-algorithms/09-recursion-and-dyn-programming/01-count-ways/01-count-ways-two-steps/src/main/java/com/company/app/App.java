package com.company.app;

/*
Fibonacci number
https://en.wikipedia.org/wiki/Fibonacci_number
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
n = (n-1) + (n-2) or (n-2) + (n-1) = n (i.e. 2+3=5)

MIT: dynamic programming I: Fibonacci, Shortest Paths
https://www.youtube.com/watch?v=OQ5jsbhAv_M
                                           6
                                    /              \
                               5                         4
                          /         \                 /      \
                       4              3             3          2
                   /      \        /     \        /   \       / \
                3          2      2      1       2     1     1   0
              /  \        / \    / \    / \     / \   / \   / \
            2     1      1  0   1  0   0  -1   1  0  0 -1  0  -1
           / \   / \    / \    / \            / \
         1   0  0  -1  0  -1  0  -1          0  -1
       /  \
      0  -1
 */
public class App
{
    static int cw = 0;
    static int countWays(int n){
        System.out.print(++cw + " ");
        if(n < 0) return 0;
        else if(n == 0) return 1;
        else return countWays(n - 1) + countWays(n - 2);
    }

    static int cwdp = 0;
    static int countWaysDP(int n, int[] map){
        System.out.print(++cwdp + " ");
        if(n < 0) return 0;
        else if(n == 0) return 1;
        else if(map[n] > -1) return map[n];
        else{
            map[n] = countWaysDP(n - 1, map) + countWaysDP(n - 2, map);
            return map[n];
        }
    }
    static int count = 4;
    public static void main( String[] args ) {
        recursiveTest();
        dpLoop();
    }

    private static void dpLoop() {
        System.out.println("#dp loop");
        for(int i = 1; i <= count; i++){
            int[] map = new int[count + 1];
            for(int j = 0 ; j < map.length; j++){
                map[j] = -1;
            }
            System.out.print("(" + i + " steps: {fn calls ");
            int c = countWaysDP(i, map);
            System.out.print("} " + c + " ways)\n");
        }
    }

    private static void recursiveTest() {
        recursiveStepByStep();
        recursiveLoop();
    }

    private static void recursiveLoop() {
        System.out.println("#recursive for recursiveLoop");
        for(int i = 1; i <= count; i++){
            System.out.print("(" + i + " steps: {fn calls: ");
            int c = countWays(i);
            System.out.print("} " + c + " ways)\n");
        }
    }
    private static void recursiveStepByStep() {
        System.out.println("#recursive recursiveStepByStep");
        System.out.print("(1 steps: ");
        System.out.print(countWays(1) + " ways)\n");
        System.out.print("(2 steps: ");
        System.out.print(countWays(2) + " ways)\n");
        System.out.print("(3 steps: ");
        System.out.print(countWays(3) + " ways)\n");
        System.out.print("(4 steps: ");
        System.out.print(countWays(4) + " ways)\n");
        System.out.print("(5 steps: ");
        System.out.print(countWays(5) + " ways)\n");
    }
}

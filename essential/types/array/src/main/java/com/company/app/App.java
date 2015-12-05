package com.company.app;

/*
 */
public class App 
{
    public static void main( String[] args )
    {
        singleDimensional();

        twoDimensional();
    }

    private static void singleDimensional() {
        System.out.println("singleDimensional");
        int[] numbers = {0, 11, 22, 33, 44, 55, 66, 77, 88, 99};
        for(int n : numbers){
            System.out.print(n + " ");
        }
    }

    /*
    the first [] is for rows
    the second [] is an array for each row
    board[1,2] - index is at the second row, and the third element in the second row
    0 0 0
    0 0 X
    0 0 0
     */
    private static void twoDimensional() {
        System.out.println("\n\ntwoDimensional");
        int board[][] = {{1,2,3},{4,5,6},{7,8,9}};
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        /*
        1 2 3
        4 5 6
        7 8 9
         */

        String data[][] = new String[][]{
                new String[]{"one", "1", "o"}, new String[]{"two", "2", "t"}, new String[]{"three","3", "t"}
        };
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // data2 is the same as above
        String data2[][] = new String[][]{
                {"one", "1", "o"}, {"two", "2", "t"}, {"three","3", "t"}
        };
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(data2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        /*
        one 1 o
        two 2 t
        three 3 t
         */


        int[][] matrix = new int[3][3];
        for(int i = 0; i < 3; i++){
            matrix[i][0] = i * 2;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        /*
        0 0 0
        2 0 0
        4 0 0
         */

        int[][] matrix2 = new int[3][3];
        for(int i = 0; i < 3; i++){
            matrix2[0][i] = i * 2;
        }
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }
        /*
        0 2 4
        0 0 0
        0 0 0
         */
    }
}
/*
output:
singleDimensional
0 11 22 33 44 55 66 77 88 99

twoDimensional
1 2 3
4 5 6
7 8 9

one 1 o
two 2 t
three 3 t

one 1 o
two 2 t
three 3 t

0 0 0
2 0 0
4 0 0

0 2 4
0 0 0
0 0 0
 */

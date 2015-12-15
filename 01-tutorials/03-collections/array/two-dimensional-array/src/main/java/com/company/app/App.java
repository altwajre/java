package com.company.app;

public class App
{
    /*
    output:
(i=0,j=0) 1 (i=0,j=1) 2 (i=0,j=2) 3
(i=1,j=0) 11 (i=1,j=1) 22 (i=1,j=2) 33
(i=2,j=0) 111 (i=2,j=1) 222 (i=2,j=2) 333
     */
    public static void main( String[] args )
    {
        int[][] multi = {
                {1,2,3},
                {11,22,33},
                {111,222,333}
        };
        for(int i = 0; i < multi.length; i++){
            for(int j = 0; j < multi[i].length; j++){
                System.out.print("(i=" + i + ",j=" + j + ") " + multi[i][j] + " ");
            }
            System.out.println("");
        }
    }
}

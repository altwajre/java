package com.company.app;

// handle runtime exception
public class App
{
    public static void main( String[] args )
    {
        int arr[] = {1,2,3,4,5};
        try{
            // My array has only 5 elements, but try to display 8th element. It should throw ArrayIndexOutOfBoundsException
            System.out.println(arr[7]);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("The specified index does not exist in array. Please correct the error.");
        }
    }
}
/*
output:
The specified index does not exist in array. Please correct the error.
 */
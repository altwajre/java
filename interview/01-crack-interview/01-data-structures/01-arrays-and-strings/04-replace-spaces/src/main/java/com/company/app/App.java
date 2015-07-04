package com.company.app;

/*
Q: replace all spaces with '%20'. assume the string has sufficient space at the end of the string
to hold additional chars

 */
public class App 
{
    public static void main( String[] args )
    {
        char[] s = "hi, this is tom          ".toCharArray();
        System.out.println(s);
        replaceSpaces(s, 15);
        System.out.println(s);
    }
    static void replaceSpaces(char[] str, int length){
        int spaceCount = 0;
        int endIndex;
        for(int i = 0; i < length; i++){
            if(str[i] == ' '){
                spaceCount++;
            }
        }
        endIndex = length + spaceCount * 2;
        str[endIndex] = '\0';
        for(int i = length - 1; i >= 0; i--){
            if(str[i] == ' '){
                str[endIndex - 1] = '0';
                str[endIndex - 2] = '2';
                str[endIndex - 3] = '%';
                endIndex -= 3;
            }
            else{
                str[endIndex - 1] = str[i];
                endIndex--;
            }
        }
    }
}

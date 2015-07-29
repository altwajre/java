package com.company.app;

public class App
{
    public enum FlowDirection{
        RightToLeft, LeftToRight
    }
    public static void main( String[] args )
    {
        System.out.println(FlowDirection.LeftToRight);
        System.out.println("done");
    }
}

package com.company.app;

import java.util.List;

class RodCutterException extends RuntimeException{}
class RodCutter{
    private boolean mustFail;
    public RodCutter(final boolean fail){
        mustFail = fail;
    }
    public void setPrices(final List<Integer> prices){
        if(mustFail){
            throw new RodCutterException();
        }
    }
    public int maxProfit(final int length){
        if(length == 0){
            throw new RodCutterException();
        }
        return 0;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "run RodCutterTest in test package" );
    }
}

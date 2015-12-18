package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        int itemsBought = 0;
        int funds = 100;
        for(int price = 10; funds >= price; price += 10){
            itemsBought++;
            funds -= price;
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Money left over: " + funds + " cents");
    }
}
/*
output:
4 items bought.
Money left over: 0 cents
 */
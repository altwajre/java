package com.company.app;

import java.math.BigDecimal;

public class App
{
    public static void main( String[] args )
    {
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsdBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for(BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)){
            itemsdBought++;
            funds = funds.subtract(price);
        }
        System.out.println(itemsdBought + " items bought.");
        System.out.println("Money left over: $" + funds);
    }
}
/*
output:
4 items bought.
Money left over: $0.00
 */

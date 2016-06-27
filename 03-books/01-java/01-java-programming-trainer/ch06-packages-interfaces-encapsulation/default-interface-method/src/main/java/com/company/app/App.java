package com.company.app;

interface Payable {
    default public boolean increasePay(int percent){
        System.out.println("Payable.increasePay() percent=" + percent);
        return true;
    }
    static double checkThePayIncreaseLimit() {
        return 123.00;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Payable payable = new Payable() {
        };

        payable.increasePay(8);
        System.out.println(Payable.checkThePayIncreaseLimit());
    }
}

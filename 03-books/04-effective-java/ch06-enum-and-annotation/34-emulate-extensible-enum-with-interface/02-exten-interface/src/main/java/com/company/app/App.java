package com.company.app;

import java.util.Arrays;
import java.util.Collection;

// Emulated extension enum
interface Operation{
    double apply(double x, double y);
}
enum ExtendedOperation implements Operation{
    EXP("^"){
        public double apply(double x, double y) { return Math.pow(x, y); }
    },
    REMAINDER("%"){
        public double apply(double x, double y) { return x % y; }
    };
    private final String symbol;
    ExtendedOperation(String symbol){ this.symbol = symbol; }
    @Override
    public String toString(){ return symbol; }
}
public class App 
{
    public static void main( String[] args )
    {
        double x = 4;
        double y = 2;
        test(ExtendedOperation.class, x, y);

        System.out.println();
        test2(Arrays.asList(ExtendedOperation.values()), x, y);
    }
    // test parameter is a bounded type token
    private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y){
        System.out.println("***test***");
        for(Operation op : opSet.getEnumConstants()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
    private static void test2(Collection<? extends Operation> opSet, double x, double y){
        System.out.println("***test2***");
        for(Operation op : opSet){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}

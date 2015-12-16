package com.company.app;

import java.util.HashMap;
import java.util.Map;

// Enum type with constant-specific class bodies and data
enum Operation{
    PLUS("+"){ double apply(double x, double y){ return x + y; }},
    MINUS("-"){ double apply(double x, double y){ return x - y; }},
    TIMES("*"){ double apply(double x, double y){ return x * y; }},
    DIVIDE("/"){ double apply(double x, double y){ return x / y; }};
    private final String symbol;
    Operation(String symbol){ this.symbol = symbol; }
    @Override
    public String toString(){ return symbol; }
    abstract double apply(double x, double y);
    // Implementing a fromString method on an enum type
    private static final Map<String, Operation> stringToEnum = new HashMap<String, Operation>();
    static { // Initialize map from constant name to enum constant
        for(Operation op : values()){
            stringToEnum.put(op.toString(), op);
        }
    }
    // Returns Operation for string, or null if string is invalid
    public static Operation fromString(String symbol){
        return stringToEnum.get(symbol);
    }
}
public class App
{
    public static void main( String[] args )
    {
        double x = 4;
        double y = 2;
        for(Operation op : Operation.values()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x,y));
        }
        System.out.printf("1+2=%s%n", Operation.PLUS.apply(1,2));
    }
}
/*
output:
4.000000 + 2.000000 = 6.000000
4.000000 - 2.000000 = 2.000000
4.000000 * 2.000000 = 8.000000
4.000000 / 2.000000 = 2.000000
1+2=3.0
 */

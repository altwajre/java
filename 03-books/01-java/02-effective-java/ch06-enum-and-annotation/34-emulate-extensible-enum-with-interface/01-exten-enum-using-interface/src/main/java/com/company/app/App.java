package com.company.app;

// Emulated extensible enum using an interface
interface Operation{
    double apply(double x, double y);
}
enum BasicOperation implements Operation{
    PLUS("+"){
        public double apply(double x, double y){
            return x + y;
        }
    },
    MINUS("-"){
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*"){
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/"){
        public double apply(double x, double y) {
            return x / y;
        }
    };
    private final String symbol;
    BasicOperation(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString(){
        return symbol;
    }
}
public class App
{
    public static void main( String[] args )
    {
        double x = 4;
        double y = 2;
        for(BasicOperation op : BasicOperation.values()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
        System.out.printf("1+2=%s%n", BasicOperation.PLUS.apply(1, 2));
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

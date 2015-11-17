package com.company.app;

public class App
{
    public interface IMath{  // Subject interface
        double add(double x, double y);
        double sub(double x, double y);
        double mul(double x, double y);
        double div(double x, double y);
    }
    static class Math implements IMath{  // Real Subject
        public double add(double x, double y) { return x + y; }
        public double sub(double x, double y) { return x - y; }
        public double mul(double x, double y) { return x * y; }
        public double div(double x, double y) { return x / y; }
    }
    static class MathProxy implements IMath{  // Proxy Object
        private Math math = new Math();
        public double add(double x, double y) { return math.add(x, y); }
        public double sub(double x, double y) { return math.sub(x, y); }
        public double mul(double x, double y) { return math.mul(x, y); }
        public double div(double x, double y) { return math.div(x, y); }
    }
    public static void main( String[] args )
    {
        MathProxy proxy = new MathProxy();
        System.out.format("4 + 2 = %s\n", proxy.add(4, 2));
        System.out.format("4 - 2 = %s\n", proxy.sub(4, 2));
        System.out.format("4 * 2 = %s\n", proxy.mul(4, 2));
        System.out.format("4 / 2 = %s\n", proxy.div(4, 2));
    }
}

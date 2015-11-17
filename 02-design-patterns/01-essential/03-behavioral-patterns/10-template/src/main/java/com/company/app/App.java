package com.company.app;

public class App
{
    static abstract class AbstractClass{
        public abstract void primitiveOperation1();
        public abstract void primitiveOperation2();
        public void templateMethod(){
            primitiveOperation1();
            primitiveOperation2();
            System.out.println("");
        }
    }
    static class ConcreteClassA extends AbstractClass{
        @Override
        public void primitiveOperation1() {
            System.out.println("ConcreteClassA.primitiveOperation1()");
        }
        @Override
        public void primitiveOperation2() {
            System.out.println("ConcreteClassA.primitiveOperation2()");
        }
    }
    static class ConcreteClassB extends AbstractClass{
        @Override
        public void primitiveOperation1() {
            System.out.println("ConcreteClassB.primitiveOperation1()");
        }
        @Override
        public void primitiveOperation2() {
            System.out.println("ConcreteClassB.primitiveOperation2()");
        }
    }
    public static void main( String[] args )
    {
        AbstractClass aA = new ConcreteClassA();
        aA.templateMethod();
        AbstractClass aB = new ConcreteClassB();
        aB.templateMethod();
    }
}

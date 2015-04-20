import java.util.Arrays;
import java.util.List;

public class MethodReference {
    static public class Apple {
        private int weight = 0;
        private String color = "";
        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }
        public String toString() {
            return "Apple{color='" + color + "', weight=" + weight + "}";
        }
    }
    static class MyClass {
        public static String staticMethod(){
            return "Test MyClass.staticMethod()";
        }
    }
    interface ITest{
        void run();
    }
    interface ITestStatic {
        String run();
    }
    private static void staticMethod() {
        System.out.println("#Test Static Method");
        ITestStatic test = () -> "Test lambda";
        System.out.println(test.run());  // Test lambda
        ITestStatic test2 = MyClass::staticMethod;  // method reference - reference a static method of static class
        System.out.println(test2.run());  // Test MyClass.staticMethod()
    }
    interface ITestInstance{
        String run(Apple apple);
    }
    private static void instanceMethod() {
        System.out.println("#Test Instance Method");
        Apple apple = new Apple(80, "green");
        ITestInstance test1 = (Apple a) -> a.toString();
        System.out.println(test1.run(apple));  // Apple{color='green', weight=80}
        ITestInstance test2 = Apple::toString;
        System.out.println(test2.run(apple));  // Apple{color='green', weight=80}
    }
    private static void compareRefStaticVsInstance() {
        ITestStatic testStatic = MyClass::staticMethod;  // method reference - reference a static method of static class
        System.out.println(testStatic.run());  // Test MyClass.staticMethod()
        Apple apple = new Apple(80, "green");
        ITestInstance testInstance = Apple::toString;
        System.out.println(testInstance.run(apple));  // Apple{color='green', weight=80}
    }
    public static void main(String[] args) {
        compareRefStaticVsInstance();
        staticMethod();  // reference static method
        instanceMethod();  // reference instance method
    }
}

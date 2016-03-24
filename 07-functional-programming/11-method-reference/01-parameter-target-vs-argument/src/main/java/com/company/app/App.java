package com.company.app;

import java.util.Arrays;
import java.util.List;

/*
Java compiler synthesizes the appropriate method in place.
To decide how to route the parameter, the Java compiler will check whether the method is an instance method or
a static method.
If it's instance method, parameter becomes the call's target as parameter.toUpperCase
If it's static method, parameter is routed as an argument to the method as App.printLenght(parameter)
 */
public class App {
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry");
    static void printLength(String s){
        System.out.println(s.length());
    }
    private static void staticMethod() {
        friends.stream()
                .forEach(App::printLength);
    }
    private static void instanceMethod() {
        friends.stream()
                // the parameter to the synthesized method turned into the target of the method call as parameter.toUppercase()
                // base the method reference is based on a class name
                .map(String::toUpperCase)
                // use the parameter of the synthesized method as an argument to the referenced method as System.out.println(parameter)
                .forEach(System.out::println);
    }
    public static void main(String[] args) {
        System.out.println("#instanceMethod");
        instanceMethod();
        System.out.println("#staticMethod");
        staticMethod();
    }
}
/*
output:
#instanceMethod
TOM
DICK
HARRY
#staticMethod
3
4
5

note:
While this parameter routing is quite convenient, there is one caveat - method collisions and the resulting ambiguity.
If there's both a matching instance method and a static method, we'll get a compilation error due to the reference's
ambiguity. For example, if we write Double::toString to convert an instance of Double to a String, the compiler would
get confused whether to use the public String toString() instance method or the static method public static
String toString(double value), both from the Double class. If we run into this, we simply switch back to using the
appropriate lambda-expression version to move on.
 */


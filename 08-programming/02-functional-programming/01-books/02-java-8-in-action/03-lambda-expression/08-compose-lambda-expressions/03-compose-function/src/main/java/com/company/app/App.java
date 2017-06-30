package com.company.app;

import java.util.function.Function;

class Letter{
    public static String addHeader(String text){
        return "Header: " + text;
    }
    public static String addFooter(String text){
        return text + " Footer";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("labda", "lambda");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        // andThen() method
        Function<Integer, Integer> h = f.andThen(g); // In math you'd write g(f(x))
        int result = h.apply(1);
        System.out.println(result); // This returns 4

        // compose() method
        Function<Integer, Integer> c = f.compose(g); // In math you'd write f(g(x))
        int result2 = c.apply(1);
        System.out.println(result2); // This returns 3

        // addHeaher, checkSpelling, addFooter
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("this is labda")); // Header: this is lambda Footer

        // addHeader, addFooter
        Function<String, String> headerFooterTranPipeLine = addHeader
                .andThen(Letter::addFooter);
        System.out.println(headerFooterTranPipeLine.apply("this is labda")); // Header: this is labda Footer
    }
}
/*
output:
4
3
Header: this is lambda Footer
Header: this is labda Footer
 */

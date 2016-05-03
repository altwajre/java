package com.company.app;

import java.util.function.Supplier;

public class App
{
    public static boolean evaluate(final int value){
        System.out.println("evaluating..." + value);
        simulateTimeConsumingOp(2000);
        return value > 100;
    }
    public static void simulateTimeConsumingOp(final int millseconds){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void eagerEvaluator(final boolean input1, final boolean input2){
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }
    /*
    for short-circuiting scenarios, we can use lambda to delay or avoid the time-consuming arguments evaluation
     */
    public static void lazyEvaluator(final Supplier<Boolean> input1, final Supplier<Boolean> input2){
        System.out.println("lazyEvaluator called...");
        System.out.println("accept?: " + (input1.get() && input2.get()));
    }
    public static void main( String[] args )
    {
        // it takes time for following method arguments evaluate(1) and evaluate(2) to evaluate, and it evaluates both
        // before get into method body
        System.out.println("#eagerEvaluator");
        eagerEvaluator(evaluate(1), evaluate(2));

        // it delays the time-consuming arguments evaluation by using lambda, and only the evaluate(1) is executed due to
        // short-circuiting which avoid evaluating the evaluate(2) that is cost saving of the lazy evaluation.
        System.out.println("#lazyEvaluator");
        lazyEvaluator(() -> evaluate(1), () -> evaluate(2));
    }
}
/*
output:
#eagerEvaluator
evaluating...1
evaluating...2
eagerEvaluator called...
accept?: false
#lazyEvaluator
lazyEvaluator called...
evaluating...1
accept?: false
 */


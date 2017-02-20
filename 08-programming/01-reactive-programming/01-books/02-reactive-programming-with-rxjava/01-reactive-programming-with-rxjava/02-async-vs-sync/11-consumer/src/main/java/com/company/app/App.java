package com.company.app;

import java.util.function.Consumer;

// https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html

// Functional Interface: This is a functinoal interface and can therefore be used as the assignment target
// for a lambda expression or method reference.

class Callback {
    private Consumer<String> consumer = x -> {}; // x -> {} means do nothing
    Callback onConsumer(Consumer<String> consumer){
        this.consumer = consumer;
        return this;
    }
    public Consumer<String> getConsumer() {
        return consumer;
    }
}
public class App
{
    public static void main( String[] args )
    {
        test_consumer();

        test_callback();
    }

    private static void test_callback() {
        System.out.println("#test_callback");
        Callback callback = new Callback();
        callback.onConsumer(v -> System.out.println("callback: " + v));
        // Consumer.accept() invokes callback
        callback.getConsumer().accept("task");
    }

    private static void test_consumer() {
        System.out.println("#test_consumer");
        Consumer<String> consumer = x -> System.out.println(x);
        // Consumer.accept() invokes callback
        consumer.accept("task");
    }
}
/*
output:
#test_consumer
task
#test_callback
callback: task
 */

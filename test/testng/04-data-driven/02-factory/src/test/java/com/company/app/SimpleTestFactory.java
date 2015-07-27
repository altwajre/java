package com.company.app;

import org.testng.annotations.Factory;

public class SimpleTestFactory {
    @Factory
    public Object[] factoryMethod(){
        return new Object[]{
                new SimpleTest("One"),
                new SimpleTest("Two")
        };
    }
}

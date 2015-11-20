package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

import java.util.logging.Logger;

class ConsoleLogger{
    private final Logger logger;  // built-in binding
    @Inject
    public ConsoleLogger(Logger logger){
        this.logger = logger;
    }
    public void log(){
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.logger.getName());
    }
}
class MyLogger{
    private String name;
    public MyLogger(){
        this.name = this.getClass().getCanonicalName();
    }
    public String getName() {
        return name;
    }
}
class MyConsoleLogger{
    private final MyLogger logger;
    @Inject
    public MyConsoleLogger(MyLogger logger){
        this.logger = logger;
    }
    public void log(){
        System.out.println(this.logger.getName());
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(ConsoleLogger.class).log();
        Guice.createInjector().getInstance(MyConsoleLogger.class).log();
    }
}
/*
https://github.com/google/guice/wiki/BuiltInBindings

output:
ConsoleLogger
com.company.app.ConsoleLogger
com.company.app.MyLogger
com.company.app.MyLogger
 */

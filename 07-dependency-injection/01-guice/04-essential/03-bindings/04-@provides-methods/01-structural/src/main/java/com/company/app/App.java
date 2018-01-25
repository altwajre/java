package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provides;

interface Logger {
    void log();
}
class DatabaseLogger implements Logger {
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
class XmlLogger implements Logger {
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
// map interfaces to implementations
class LoggerModule extends AbstractModule {
    @Override
    protected void configure() {
    }
    @Provides
    Logger provideLogger(){
        return new DatabaseLogger();
    }
}
public class App 
{
    public static void main( String[] args )
    {
        Guice.createInjector(new LoggerModule()).getInstance(Logger.class).log();
    }
}
/*
https://github.com/google/guice/wiki/ProvidesMethods
When you need code to create an object, use an @Provides method. The method must be defined within a module, and it must
have an @Provides annotation. The method's return type is the bound type. Whenever the injector needs an instance of
that type, it will invoke the method.

output:  LoggerModule.@Provides method returns new DatabaseLogger()
DatabaseLogger
 */

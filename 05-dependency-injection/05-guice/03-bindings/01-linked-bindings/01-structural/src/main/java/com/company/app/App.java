package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

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
class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        // map interface Logger to implementation DatabaseLogger
        bind(Logger.class).to(DatabaseLogger.class);
    }
}
class XmlModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Logger.class).to(XmlLogger.class);
    }
}

public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new DatabaseModule()).getInstance(Logger.class).log();
        Guice.createInjector(new XmlModule()).getInstance(Logger.class).log();
    }
}
/*
https://github.com/google/guice/wiki/LinkedBindings
Linked bindings map a type to its implementation.

output:
DatabaseLogger
XmlLogger
 */

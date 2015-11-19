package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

interface Logger {
    void log();
}
class DatabaseLogger implements Logger {
    @Inject
    public DatabaseLogger(){
        System.out.println("Called DatabaseLogger constructor");
    }
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
class XmlLogger implements Logger {
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(DatabaseLogger.class).log();
    }
}
/*
https://github.com/google/guice/wiki/JustInTimeBindings
When the injector needs an instance of a type, it needs a binding. The bindings in a modules are called explicit
bindings, and the injector uses them whenever they're available. If a type is needed but there isn't an explicit
binding, the injector will attempt to create a Just-In-Time binding. Also known as JIT bindings and implicit bindings.

Guice can create bindings for concrete types by using the type's injectable constructor. This is either a non-private,
no-arguments constructor, or a constructor with the @Inject annotation.

output:
Called DatabaseLogger constructor
DatabaseLogger
 */

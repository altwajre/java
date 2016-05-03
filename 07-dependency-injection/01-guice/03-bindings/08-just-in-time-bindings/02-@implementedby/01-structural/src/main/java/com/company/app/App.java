package com.company.app;

import com.google.inject.Guice;
import com.google.inject.ImplementedBy;
import com.google.inject.Inject;

@ImplementedBy(DatabaseLogger.class)
interface Logger {
    void log();
}
class DatabaseLogger implements Logger {
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
        Guice.createInjector().getInstance(Logger.class).log();
    }
}
/*
https://github.com/google/guice/wiki/JustInTimeBindings
When the injector needs an instance of a type, it needs a binding. The bindings in a modules are called explicit
bindings, and the injector uses them whenever they're available. If a type is needed but there isn't an explicit
binding, the injector will attempt to create a Just-In-Time binding. Also known as JIT bindings and implicit bindings.

@ImplementedBy annotate types tell the injector what their default implementation type is. It acts like a linked
binding, specifying the subtype to use when building a type.

output:
Called DatabaseLogger constructor
DatabaseLogger
 */

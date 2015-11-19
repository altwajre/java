package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.BindingAnnotation;
import com.google.inject.Guice;
import com.google.inject.Key;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

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
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface Database {}
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface Xml {}
// map interfaces to implementations
class LoggerModule extends AbstractModule {
    @Override
    protected void configure() {
        // map interface Logger to implementation DatabaseLogger
        bind(Logger.class).annotatedWith(Database.class).to(DatabaseLogger.class);
        bind(Logger.class).annotatedWith(Xml.class).to(XmlLogger.class);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new LoggerModule())
                .getInstance(Key.get(Logger.class, Database.class))
                .log();
        Guice.createInjector(new LoggerModule())
                .getInstance(Key.get(Logger.class, Xml.class))
                .log();
    }
}
/*
https://github.com/google/guice/wiki/BindingAnnotations
Bindings support an optional binding annotation. The annotation and type together uniquely identify a binding. This
pair is called a key.

output:
DatabaseLogger
XmlLogger
 */

package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Provider;

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
class DatabaseLoggerProvider implements Provider<Logger>{
    public Logger get() {
        return new DatabaseLogger();
    }
}
// map interfaces to implementations
class LoggerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Logger.class).toProvider(DatabaseLoggerProvider.class);
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
https://github.com/google/guice/wiki/ProviderBindings
When your @Provides methods start to grow complex, you may consider moving them to a class of their own. The provider
class implements Guice's Provider interface.

output:
DatabaseLogger
 */

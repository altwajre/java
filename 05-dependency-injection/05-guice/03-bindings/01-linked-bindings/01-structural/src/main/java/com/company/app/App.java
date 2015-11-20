package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

interface TransactionLog {
    void log();
}
class DatabaseTransactionLog implements TransactionLog {
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
class SqlDatabaseTransactionLog extends DatabaseTransactionLog {}
class XmlTransactionLog implements TransactionLog {
    public void log() {
        System.out.println(this.getClass().getSimpleName());
    }
}
// map interfaces to implementations
class DatabaseModule extends AbstractModule {
    @Override
    protected void configure() {
        // map interface TransactionLog to implementation DatabaseTransactionLog
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
    }
}
class LinkModule extends AbstractModule{
    @Override
    protected void configure() {
        // map interface TransactionLog to implementation DatabaseTransactionLog
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
        // link DatabaseTransactionLog class to a subclass MySqlDatabaseDatabaseTransactionLog
        bind(DatabaseTransactionLog.class).to(SqlDatabaseTransactionLog.class);
    }
}
class XmlModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(TransactionLog.class).to(XmlTransactionLog.class);
    }
}

public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new DatabaseModule()).getInstance(TransactionLog.class).log();
        Guice.createInjector(new LinkModule()).getInstance(TransactionLog.class).log();
        Guice.createInjector(new XmlModule()).getInstance(TransactionLog.class).log();
    }
}
/*
https://github.com/google/guice/wiki/LinkedBindings
Linked bindings map a type to its implementation.

output:
DatabaseTransactionLog
SqlDatabaseTransactionLog
XmlTransactionLog
 */

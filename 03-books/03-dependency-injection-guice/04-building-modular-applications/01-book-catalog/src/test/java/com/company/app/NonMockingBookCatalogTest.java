package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class TestBooksModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Library.class).to(DatabaseLibrary.class);
        bind(BookCatalog.class).to(SimpleBookCatalog.class);
    }
}
public class NonMockingBookCatalogTest {
    private Injector injector;
    @Before
    public final void setup(){
        injector = Guice.createInjector(new TestBooksModule());
    }
    @Test
    public final void freeFormBookSearch(){
        BookCatalog bookCatalog = new SimpleBookCatalog(injector.getInstance(Library.class));
        bookCatalog.search("..");
    }
/*
output:
SimpleBookCatalog.search()
unknown criteria
 */
}
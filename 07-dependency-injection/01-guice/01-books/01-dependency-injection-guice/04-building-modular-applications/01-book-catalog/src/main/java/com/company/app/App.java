package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;

class Book{
    final String name;
    public Book(String name) {
        this.name = name;
    }
}
/**
 * BookCatalog refers only to the interface Library. Its search() method translates some free-form text search criteria
 * into a search by title, author, or ISBN, subsequently calling the appropriate method on Library.
 */
interface BookCatalog{
    Book search(String criteria);
}
class SimpleBookCatalog implements BookCatalog{
    final Library library;
    @Inject
    public SimpleBookCatalog(Library library) {
        this.library = library;
    }
    @Override
    public Book search(String criteria) {
        System.out.println("SimpleBookCatalog.search()");
        Book book = null;
        switch(criteria.toLowerCase()){
            case "findbytitle":
                book = library.findByTitle("foo");
                break;
            case "findbyauthor":
                book = library.findByAuthor("foo");
                break;
            case "findbyisbn":
                book = library.findByIsbn("123");
                break;
            default:
                System.out.println("unknown criteria");
        }
        return book;
    }
}
class DesktopGUIBookCatalog implements BookCatalog{
    final Library library;
    @Inject
    public DesktopGUIBookCatalog(Library library) {
        this.library = library;
    }
    @Override
    public Book search(String criteria) {
        return null;
    }
}
interface Library{
    Book findByTitle(String title);
    Book findByAuthor(String title);
    Book findByIsbn(String title);
}
class DatabaseLibrary implements Library{
    @Override
    public Book findByTitle(String title) {
        return null;
    }
    @Override
    public Book findByAuthor(String title) {
        return null;
    }
    @Override
    public Book findByIsbn(String title) {
        return null;
    }
}
class SimpleBookCatalogBooksModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Library.class).to(DatabaseLibrary.class);
        bind(BookCatalog.class).to(SimpleBookCatalog.class);
    }
}
/**
 * When we use DesktopGUIBookCatalogBooksModule, the Library is oblivious to the changes in our application.
 * Loose coupling enables any of our services to evolve down their own paths and yet remain verifiable and behaviorally
 * consistent, performing the intended service for end users.
 */
class DesktopGUIBookCatalogBooksModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Library.class).to(DatabaseLibrary.class);
        bind(BookCatalog.class).to(DesktopGUIBookCatalog.class);
    }
}
public class App {
    public static void main(String... args){
        BookCatalog bookCatalog = Guice.createInjector(new SimpleBookCatalogBooksModule())
                .getInstance(BookCatalog.class);
        bookCatalog.search("findByTitle");
    }
}
/*
Loose coupling with dependency injection

output:
SimpleBookCatalog.search()
 */

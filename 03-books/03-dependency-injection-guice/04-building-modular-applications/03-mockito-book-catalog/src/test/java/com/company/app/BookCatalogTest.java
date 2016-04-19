package com.company.app;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

class Book{
    final String name;
    public Book(String name) {
        this.name = name;
    }
}
interface BookCatalog{
    Book search(String criteria);
}
class SimpleBookCatalog implements BookCatalog{
    final Library mock;
    public SimpleBookCatalog(Library mock){
        this.mock = mock;
    }
    public Book search(String criteria) {
        mock.findByAuthor(criteria);
        mock.findByKeyword(criteria);
        mock.findByTitle(criteria);
        return null;
    }
}
interface Library{
    Book findByTitle(String title);
    Book findByAuthor(String title);
    Book findByIsbn(String title);
    Book findByKeyword(String criteria);
    String foo(String msg);
}
public class BookCatalogTest {
    @Test
    public final void freeFormBookSearch(){
        Library mock = mock(Library.class);
        String criteria = "dependency injection";
        when(mock.findByAuthor(criteria)).thenReturn(null);
        when(mock.findByKeyword(criteria)).thenReturn(null);
        Book di = new Book("dependency injection");
        when(mock.findByTitle(criteria)).thenReturn(di);

        new SimpleBookCatalog(mock).search(criteria);

        verify(mock).findByAuthor(criteria);
        verify(mock).findByKeyword(criteria);
        verify(mock).findByTitle(criteria);
    }
}

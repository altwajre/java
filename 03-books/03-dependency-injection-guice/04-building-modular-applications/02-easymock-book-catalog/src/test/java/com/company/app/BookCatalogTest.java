package com.company.app;

import org.junit.Test;
import static org.easymock.EasyMock.*;

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
    public SimpleBookCatalog(Library mock){}
    public Book search(String criteria) {
        return null;
    }
}
interface Library{
    Book findByTitle(String title);
    Book findByAuthor(String title);
    Book findByIsbn(String title);
    Book findByKeyword(String criteria);
}
public class BookCatalogTest {
    @Test
    public final void freeFormBookSearch(){
        Library mock = createStrictMock(Library.class);
        String criteria = "dependency injection";

        expect(mock.findByAuthor(criteria))
                .andReturn(null);

        expect(mock.findByKeyword(criteria))
                .andReturn(null);

        Book di = new Book("dependency injection");
        expect(mock.findByTitle(criteria))
                .andReturn(di);


        replay(mock);

        new SimpleBookCatalog(mock)
                .search(criteria);

        verify(mock);
    }
}

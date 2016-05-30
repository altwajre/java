package com.company.app;

import org.junit.Test;
import static org.easymock.EasyMock.*;

class Person{
    public String findName(String name){
        return name;
    }
}
public class MockingBookCatalogTest {
    @Test
    public final void freeFormBookSearch(){
        Library mock = createStrictMock(Library.class);
        String criteria = "dependency injection";
        expect(mock.findByAuthor(criteria)).andReturn(null);
//        expect(mock.findByIsbn(criteria)).andReturn(null);
//        Book di = new Book("dependency injection");
//        expect(mock.findByTitle(criteria)).andReturn(di);
        replay(mock); // signal the mock to be ready
        new SimpleBookCatalog(mock).search(criteria);
        verify(mock); // verify its usage

    }
    @Test
    public final void foo(){
        Person mock = createStrictMock(Person.class);
        String criteria = "Tom";
        expect(mock.findName(criteria)).andReturn("Tomx");
        replay(mock);
        mock.findName(criteria);
        verify(mock);

    }
}

package com.company.app;

import lombok.Value;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Value
class Library{
    private String address;
}

@Value
class Book{
    private String name;
}
public class App
{
    public static void main( String[] args )
    {
        Map<Library, Collection<Book>> libraries = new HashMap<>();

        Collection<Book> books1 = new ArrayList<>();
        books1.add(new Book("books1 book_1"));
        books1.add(new Book("books1 book_2"));

        Collection<Book> books2 = new ArrayList<>();
        books2.add(new Book("books2 book_a"));
        books2.add(new Book("books2 book_b"));

        Library library1 = new Library("library_1");
        Library library2 = new Library("library_2");

        libraries.put(library1, books1);
        libraries.put(library2, books2);

        libraries.get(library1).forEach(b -> System.out.println(b.getName()));
        libraries.get(library2).forEach(b -> System.out.println(b.getName()));

    }
}
/*
output:
books1 book_1
books1 book_2
books2 book_a
books2 book_b
 */

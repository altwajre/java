package com.company.app;

import com.google.common.collect.ImmutableList;

// http://www.javarticles.com/2015/04/guava-immutablelist.html
class Book {
    private String name;

    public Book(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Student {
    private String name;
    private ImmutableList<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    public ImmutableList<Book> getBooks() {
        return books;
    }

    public void setBooks(ImmutableList<Book> books) {
        this.books = books;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("#Create ImmutableList of books");
        Student student = new Student();
        Book wpf = new Book("WPF");
        Book python = new Book("Python");
        Book java = new Book("Java");
        student.setBooks(ImmutableList.of(wpf, python, java));

        ImmutableList<Book> books = student.getBooks();
        books.forEach(b -> System.out.println(b.getName()));

        System.out.println("#Try to add book");
        try{
            books.remove(python);
            books.forEach(b -> System.out.println(b.getName()));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
/*
output:
#Create ImmutableList of books
WPF
Python
Java
#Try to add book
java.lang.UnsupportedOperationException
 */

package com.company.app;

import java.util.ArrayList;
import java.util.List;

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
    private List<Book> books = new ArrayList<>();

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

    public List<Book> getBooks() {
        return books;
    }

}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("#Add books");
        Student student = new Student();
        Book wpf = new Book("WPF");
        student.addBook(wpf);
        Book python = new Book("Python");
        student.addBook(python);
        Book java = new Book("Java");
        student.addBook(java);

        student.getBooks().forEach(b -> System.out.println(b.getName()));

        System.out.println("#Remove python book");
        student.removeBook(python);

        List<Book> books = student.getBooks();
        books.forEach(b -> System.out.println(b.getName()));

        System.out.println("#Add new book");
        Book newBook = new Book("New Book");
        books.add(newBook);

        books.forEach(b -> System.out.println(b.getName()));
    }
}
/*
output:
#Add books
WPF
Python
Java
#Remove python book
WPF
Java
#Add new book
WPF
Java
New Book
 */

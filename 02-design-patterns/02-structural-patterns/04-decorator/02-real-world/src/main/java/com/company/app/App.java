package com.company.app;

import java.util.ArrayList;
import java.util.List;

abstract class LibraryItem<T>{  // abstract Component
    public static int NumCopies;
    public abstract void display();
}
class Book extends LibraryItem<Book>{  // ConcreteComponent
    private String author;
    private String title;
    public Book(String author, String title, int numCopies){
        this.author = author;
        this.title = title;
        this.NumCopies = numCopies;
    }
    @Override
    public void display() {
        System.out.println("\nBook ----- ");
        System.out.format(" Author: %s\n", author);
        System.out.format(" Title: %s\n", title);
        System.out.format(" # Copies: %s\n", NumCopies);
    }
}
class Video extends LibraryItem<Video>{  // ConcreteComponent
    private String director;
    private String title;
    private int playTime;
    public Video(String director, String title, int numCopies, int playTime){
        this.director = director;
        this.title = title;
        this.NumCopies = numCopies;
        this.playTime = playTime;
    }
    @Override
    public void display() {
        System.out.println("\nVideo ----- ");
        System.out.format(" Director: %s\n", director);
        System.out.format(" Title: %s\n", title);
        System.out.format(" # Copies: %s\n", NumCopies);
        System.out.format(" Playtime: %s\n", playTime);
    }
}
abstract class Decorator<T> extends LibraryItem<T>{  // abstract Decorator
    private LibraryItem<T> libraryItem;
    public Decorator(LibraryItem<T> libraryItem){
        this.libraryItem = libraryItem;
    }
    @Override
    public void display(){
        libraryItem.display();
    }
}
class Borrowable<T> extends Decorator<T> {  // Concrete Decorator
    private List<String> borrowers = new ArrayList<String>();
    public Borrowable(LibraryItem<T> libraryItem) {
        super(libraryItem);
    }
    public void borrowItem(String name){
        borrowers.add(name);
        NumCopies--;
    }
    public void returnItem(String name){
        borrowers.remove(name);
        NumCopies++;
    }
    public void display(){
        super.display();
        borrowers.forEach(b -> System.out.println(" borrower: " + b));
    }
}
public class App
{
    public static void main( String[] args )
    {
        Book book = new Book("Worley", "Inside ASP.NET", 10);
        book.display();

        Video video = new Video("Spielberg", "Jaws", 23, 92);
        video.display();

        System.out.println("\nMaking video borrowable:");

        Borrowable<Video> borrow = new Borrowable<Video>(video);
        borrow.borrowItem("Customer #1");
        borrow.borrowItem("Customer #2");
        borrow.display();
    }
}
/*
Definition
Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing
for extending functionality.

output:
Book -----
 Author: Worley
 Title: Inside ASP.NET
 # Copies: 10

Video -----
 Director: Spielberg
 Title: Jaws
 # Copies: 23
 Playtime: 92

Making video borrowable:

Video -----
 Director: Spielberg
 Title: Jaws
 # Copies: 21
 Playtime: 92
 borrower: Customer #1
 borrower: Customer #2
 */

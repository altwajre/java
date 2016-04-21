package com.company.app;

import com.google.inject.Inject;
import net.jcip.annotations.Immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// It is not immutable because the value of its title field can be changed arbitrarily by calling setTitle()
class Book{
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString(){
        return title;
    }
}

// It is immutable becuse title is declared as final; once set in the constructor, the value of title cannot change.
class ImmutableBook{
    final String title;
    public ImmutableBook(String title) {
        this.title = title;
    }
    public String getTitle(){ return title; }
}

// It is not immutable because names is an array, only the reference to it is immutable by declaring it final.
class AddressBook{
    final String[] names;
    public AddressBook(String[] names) {
        this.names = names;
    }
    public String[] getNames(){
        return names;
    }
}

// It is not immutable because we can still modify the array
class AddressBookMutator{
    final AddressBook book;
    @Inject
    public AddressBookMutator(AddressBook book) {
        this.book = book;
    }
    public void mutate(){
        String[] names = book.getNames();
        for(int i = 0; i < names.length; i++){
            names[i] = "Censored!";
        }
        for(int i = 0; i < names.length; i++){
            System.out.println(book.getNames()[i]);
        }
    }
}

// It is not immutable because we can still modify the list; see betterAddressBookTest() for the problem
// The wrapper provided by the Collections library ensures that no updates can be made to the list once it has been set.
class BetterAddressBook{
    final List<String> names;
    public BetterAddressBook(List<String> names) {
        this.names = Collections.unmodifiableList(names);
    }
    public List<String> getNames(){
        return names;
    }
}

// It is immutable because we can not modify the list since we copy the list at the time of its construction.
@Immutable
class BestAddressBook{
    final List<String> names;
    public BestAddressBook(List<String> names) {
        this.names = Collections.unmodifiableList(new ArrayList<>(names));
    }
    public List<String> getNames(){
        return names;
    }
}

/*
It is immutable because we can modify the book
The rule with immutability and object graphs is that every dependency of an object must also be immutable. In the case
of BestAddressBook, we got lucky, since Strings in Java are already immutable. Take care to ensure that every dependency
you have is safely immutable before declaring an object as such.
 */
class Library{
    final List<Book> books;
    public Library(List<Book> books) {
        this.books = Collections.unmodifiableList(new ArrayList<>(books));
    }
    public List<Book> getBooks(){
        return books;
    }
}

public class App {
    public static void main(String... args){
        betterAddressBookTest();
        bestAddressBookTest();
        libraryTest();
    }

    private static void libraryTest() {
        Book book = new Book();
        book.setTitle("Dependency Injection");
        Library library = new Library(Arrays.asList(book));
        library.getBooks().get(0).setTitle("New Title - problem"); // mutates library - changeable
        library.getBooks().forEach(System.out::println);
    }
/*
output:
New Title - problem
 */

    private static void bestAddressBookTest() {
        List<String> physicists = new ArrayList<>();
        physicists.addAll(Arrays.asList("Tom", "Dick", "Harry"));
        BestAddressBook book = new BestAddressBook(physicists);
        physicists.add("New Name - problem");

        book.getNames().forEach(System.out::println);
    }
/*
output:
Tom
Dick
Harry
 */

    private static void betterAddressBookTest() {
        List<String> physicists = new ArrayList<>();
        physicists.addAll(Arrays.asList("Tom", "Dick", "Harry"));
        BetterAddressBook book = new BetterAddressBook(physicists);
        physicists.add("New Name - problem");

        book.getNames().forEach(System.out::println);
    }
/*
output:
Tom
Dick
Harry
New Name - problem
 */
}


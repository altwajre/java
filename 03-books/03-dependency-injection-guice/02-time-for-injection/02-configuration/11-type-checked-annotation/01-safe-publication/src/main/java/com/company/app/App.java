package com.company.app;

import java.util.HashMap;
import java.util.Map;

class Email{
    final String s;
    public Email(String s) {
        this.s = s;
    }
}

class EmailDatabase{
    Email get(String name){
        return new Email(name);
    }
}
/*
This is another manifestation of the visibility problems. There are several reasons why Thread B may see a null value,
even though as far as Thread A is concerned, the map has been updated. We encountered one of these in an earlier chapter
when looking at memory coherency. Without sufficient synchronization, the JVM makes no guarantees about visibility of
fields between threads. This is a deliberate choice made by the language designers to allow for maximum flexibility in
optimizations on various platforms. Thread A's updates to the hashtable may not yet be synchronized with main memory.
Thus, they're not published to other threads. So while a thread can see its own updates, there's no assurance that
others will. Now let's look at how to correctly publish to all threads.
 */
class UnsafePublication{
    private Map<String, Email> emails = new HashMap<>();
    public void putEmails(){
        emails.put("Dhanji", new Email("dhanji@gmail.com"));
        emails.put("Josh", new Email("josh@noemail.com"));

        System.out.println("Map updated.");
        read();
    }
    public void read() {
        System.out.println("Dhanji's email address really is " + emails.get("Dhanji"));
    }
}
/*
MoreUnsafePublication is a simple variant of the hashtable that reads an email address using its dependency EmailDatabase.
This time the issue of publication is not with the hashtable values but with the EmailDatabase dependency itself. Threads
that call method read() cannot rely on the fact that the dependency is available. Without sufficient synchronization,
the thread creating the object does not safely publish its fields to other threads.
The MoreUnsafePublication.read() could easily result in a NullPointerException.
 */
class MoreUnsafePublication {
    private EmailDatabase service;
    public MoreUnsafePublication(EmailDatabase service) {
        this.service = service;
    }
    public void read() {
        System.out.println("Dhanji's email address really is " + service.get("Dhanji"));
    }
}
/*
Final fields are given the given the guarantee of safe visibility to all threads concerned. Because these fields are
generally always set in the constructor, they’ll be visible to all threads once the constructor completes.
 */
class SafePublication{
    final EmailDatabase service;
    public SafePublication(EmailDatabase service) {
        this.service = service;
    }
    public void read(){
        System.out.println("Dhanij's email address really is " + service.get("Dhanji"));
    }
}
public class App {
    public static void main(String... args){
        System.out.println("hi");
    }
}

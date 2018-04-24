package com.company.app;

import java.text.SimpleDateFormat;
import java.util.*;

// Create lots of unnecessary duplicate objects
class Person {
  private final Date birthDate;

  public Person(Date birthDate) {
    // Defensive copy
    this.birthDate = new Date(birthDate.getTime());
  }

  // Other fields, methods omitted
  // DON"T DO THIS!
  public boolean isBabyBoomer() {
    System.out.println("instance: get start_date and end_date");
    // Unnecessary allocation of expensive object
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    Date boomStart = gmtCal.getTime();
    gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
    Date boomEnd = gmtCal.getTime();
    return birthDate.compareTo(boomStart) >= 0 && birthDate.compareTo(boomEnd) < 0;
  }
}

public class App {
  public static void main(String[] args) throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    String dateInString = "31-08-1982 10:20:56";
    Person tom = new Person(sdf.parse(dateInString));
    System.out.println("Tom isBabyBoomer=" + tom.isBabyBoomer());
//    System.in.read();
    // Creating lots of Person objects and keep them alive
    List<Person> people = new ArrayList<>();
    for(int i = 0; i< 10; i++){
      final Person person = new Person(sdf.parse(dateInString));
      people.add(person);
      person.isBabyBoomer();
    }
//    System.in.read();
    System.out.println(people.size());
    System.out.println("END");
  }
}
/*
instance: get start_date and end_date
Tom isBabyBoomer=false
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
instance: get start_date and end_date
10
END

ISSUE:
DUP objects - "instance: get start_date and end_date" is called each time when person.isBabyBoomer() is invoked
 */

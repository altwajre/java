package com.company.app;

import java.text.SimpleDateFormat;
import java.util.*;

// Doesn't create unnecessary duplicate objects
class Person {
  private final Date birthDate;

  public Person(Date birthDate) {
    // Defensive copy
    this.birthDate = new Date(birthDate.getTime());
  }

  // Other fields, methods
  // The starting and ending dates of the baby boom.
  private static final Date BOOM_START;
  private static final Date BOOM_END;

  static {
    System.out.println("static: get start_date and end_date");
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_START = gmtCal.getTime();
    gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
    BOOM_END = gmtCal.getTime();
  }

  public boolean isBabyBoomer() {
    return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
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
    for (int i = 0; i < 10; i++) {
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
static: get start_date and end_date
Tom isBabyBoomer=false
10
END
 */

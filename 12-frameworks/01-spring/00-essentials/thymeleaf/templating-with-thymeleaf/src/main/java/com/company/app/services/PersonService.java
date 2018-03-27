package com.company.app.services;

import com.company.app.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

  public List<Person> getPersons() {
    List<Person> personList = new ArrayList<>();

    Person tom = new Person();
    tom.setFirstName("Tom");
    tom.setLastName("Doe");

    Person harry = new Person();
    harry.setFirstName("Harry");
    harry.setLastName("Lee");

    personList.add(tom);
    personList.add(harry);
    return personList;
  }

}

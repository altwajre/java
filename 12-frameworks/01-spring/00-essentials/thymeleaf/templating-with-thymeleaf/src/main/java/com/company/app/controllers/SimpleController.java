package com.company.app.controllers;

import com.company.app.dto.Person;
import com.company.app.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class SimpleController {

  private final PersonService personService;

  @Autowired
  public SimpleController(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping("/")
  public String index() {
    return "index"; // return template index.html
  }
  @RequestMapping("/personList")
  public String personList() {
    return "personList";
  }

  @ModelAttribute("allPersons")
  public List<Person> populatePersons() {
    return personService.getPersons();
  }

  @ModelAttribute("simpleValue")
  public String simpleValue() {
    return "Hello!";
  }
  @ModelAttribute("today")
  public LocalDate localDate() {
    return LocalDate.now();
  }
}

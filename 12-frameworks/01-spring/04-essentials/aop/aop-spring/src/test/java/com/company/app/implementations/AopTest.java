package com.company.app.implementations;

import com.company.app.interfaces.GreetingService;
import com.company.app.models.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {

  @Autowired
  private GreetingService greetingService;

  @Autowired
  private Person tom;

  @Test
  public void greet() {
    greetingService.greet();
  }

  @Test
  public void testPerson() {
    tom.setName("Tom");
    System.out.println(tom.getName());
  }
}

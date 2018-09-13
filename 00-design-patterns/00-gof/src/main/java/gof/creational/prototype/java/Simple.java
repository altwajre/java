package gof.creational.prototype.java;

import lombok.Getter;
import lombok.Setter;

interface Person {
  Person clone();
}

@Getter
@Setter
class Customer implements Person, Cloneable {
  private String name;

  @Override
  public Person clone() {
    Person person;
    try {
      person = (Person) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
    return person;
  }
}

public class Simple {
  public static void main(String[] args) {
    System.out.println("### Statue creation by Prototype design pattern ");
    System.out.println("How many Statue you want to create based on Mold / prototype ?");

    Person customer = new Customer();
    ((Customer) customer).setName("Tom");

    for (int i = 0; i < 3; i++) {
      Person person = customer.clone();
      System.out.println("created statue object with id" + person.hashCode());
      System.out.println(((Customer) person).getName());
    }
  }
}
/*
### Statue creation by Prototype design pattern
How many Statue you want to create based on Mold / prototype ?
created statue object with id491044090
Tom
created statue object with id644117698
Tom
created statue object with id1872034366
Tom
 */

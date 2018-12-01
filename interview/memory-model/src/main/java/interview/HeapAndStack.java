package interview;

class Person {
  public String firstName;
  public String lastName;

  void setNames(Person person) {
    person.firstName = "aaa";
    person.setLastName("bbb");
  }

  void setLastName(String lastName) {
    this.lastName = lastName;
    String name = "ccc";
    this.lastName = name;
  }
}

public class HeapAndStack {
  public static void main(String[] args) {
    Person person1 = null;
    person1 = new Person();
    person1.setNames(person1);

    System.out.println("person1.firstName: " + person1.firstName);
    System.out.println("person1.lastName: " + person1.lastName);
  }
}
/*
person1.firstName: aaa
person1.lastName: ccc
 */

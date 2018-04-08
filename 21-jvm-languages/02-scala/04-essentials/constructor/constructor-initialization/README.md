# Constructor Initialization

> Java vs Scala

- Java

initialization code

```
  public Person (String firstName, String lastName) {
    this.name = firstName + " " + lastName;
  }
```

Person.java

```
public class Person {
  public Person (String firstName, String lastName) {
    this.name = firstName + " " + lastName;
  }
  private String name;
  public String getName() {
    return name;
  }
}
```

App.java

```
public class App {
  public static void main(String[] args) {
    Person tom = new Person("Tom", "Lee");
    System.out.println(tom.getName());
  }
}
```

- Scala

initialization code

```
class Customer (firstName: String, lastName: String) {
  val _name = firstName + " " + lastName
```

Customer.scala

```
class Customer (firstName: String, lastName: String) {
  val _name = firstName + " " + lastName
  // Getter
  def name = _name
}
```

ScalaApp.scala

```
object ScalaApp {
  def main(args: Array[String]): Unit = {
    val tom = new Customer("Tom", "Wang")
    println(s"Scala says Hello to ${tom.name}!")
  }
}
```

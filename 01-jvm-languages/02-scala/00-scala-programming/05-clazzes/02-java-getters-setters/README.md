# Java Getters and Setters

- Use @scala.beans.BeanProperty
- Apply BeanProperty annotation to the property
- If applied to a val, BeanProperty will create a getter
- If applied to a var, BeanProperty will create a setter

## Create and using classes steps

1, create Employee class - Employee.scala

```
import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName:String, @BeanProperty var lastName:String)
```

2, compile the Employee class

```
$ scalac Employee.scala 
$ javap -p Employee
Compiled from "Employee.scala"
public class Employee {
  private final java.lang.String firstName;
  private java.lang.String lastName;
  public java.lang.String firstName();
  public java.lang.String lastName();
  public void lastName_$eq(java.lang.String);
  public java.lang.String getFirstName();
  public java.lang.String getLastName();
  public void setLastName(java.lang.String);
  public Employee(java.lang.String, java.lang.String);
}
```

3, use instance of Employee - EmployeeScript.scala

```
val ada = new Employee("Ada", "Lovelace")
println(ada.firstName)
ada.lastName = "Lee"
println(ada.lastName)
```

4, run the EmployeeScript.scala

```
$ scala -nc EmployeeScript.scala 
Ada
Lee
```

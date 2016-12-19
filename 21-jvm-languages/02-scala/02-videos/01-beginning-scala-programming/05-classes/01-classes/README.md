# Classes

- Classes are like blueprints
- val creates accessors, access to the inner state
- val creates mutators, allowing change to inner state
- javap -p is a great utility to see the Java bytecode

## Uniform Access Principle

The uniform access principle states that variables and parameterless methods should be accessed using the same syntax

## Create and using classes steps

1, create Employee class - Employee.scala

```
class Employee(firstName:String, lastName:String)
```

2, compile the Employee class

```
$ scalac Employee.scala 
$ ls
Employee.class	Employee.scala	README.md
$ javap -p Employee
Compiled from "Employee.scala"
public class Employee {
  public Employee(java.lang.String, java.lang.String);
}
```

3, use instance of Employee - EmployeeScript.scala

```
val ada = new Employee("Ada", "Lovelace")
println(ada)
```

4, run the EmployeeScript.scala

```
$ scala -nc EmployeeScript.scala <- -nc means use Employee.class directly instead of the cache
Ada
```

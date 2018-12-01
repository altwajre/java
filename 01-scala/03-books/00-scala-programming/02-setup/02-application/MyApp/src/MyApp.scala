class Employee(firstName:String, lastName:String, department: Department)

class Department(name:String)

// Right click on the fle in intellij, click Recompile 'MyApp.scala'
// or
//$ scalac MyApp.scala
//$ ls
//Department.class	Employee.class		MyApp.scala
//$ javap -p Employee.class <- inspect the .class file to view the Java code
//Compiled from "MyApp.scala"
//public class Employee {
//  public Employee(java.lang.String, java.lang.String, Department);
//}

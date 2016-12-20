import java.time._
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)

object Employee {
  def create(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)

  def create(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}

object EmployeeDesignRunner extends App {
  val employee = Employee.create("Tom", "Lee", "Programmer")
  println(employee.hireDate)
}

//$ scala 03-EmployeeDesign.scala
//2016-12-19

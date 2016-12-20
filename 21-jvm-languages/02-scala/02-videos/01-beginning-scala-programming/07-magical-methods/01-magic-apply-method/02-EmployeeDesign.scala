import java.time._
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)

object Employee {
  def apply(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)

  def apply(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}

case class Department(name:String)

object EmployeeDesignRunner extends App {
  val employee = Employee("Tom", "Lee", "Programmer")
  println(employee.hireDate)

  val applyDepartment = Department.apply("apply()")
  val department = Department("Sports")
  println(applyDepartment)
  println(department)
}

//$ scala 02-EmployeeDesign.scala
//2016-12-19
//Department(apply())
//Department(Sports)

package com.company.app._07MagicalMethods

import java.time.LocalDate

// when call apply(), you can call it without method name `apply`
class Foo(x:Int) {
  def apply(y:Int) = x + y
}

object MagicApply extends App {
  val foo = new Foo(10)
  // cal with apply()
  println(foo.apply(20))
  // 30

  // when call apply(), you can call it without method name `apply`
  println(foo(21))
  // 31
}

//====================================================

// apply() method can make companion object acts like a `case class`
class Employee protected (val firstName:String, val lastName:String, val title:String, val hireDate:LocalDate)

object Employee {
  def apply(firstName:String, lastName:String, title:String) =
    new Employee(firstName, lastName, title, LocalDate.now)

  def apply(firstName:String, lastName:String, title:String, hireDate:LocalDate) =
    new Employee(firstName, lastName, title, hireDate)
}

case class Department(name:String)

object EmployeeDesignRunner extends App {
  // apply() method can make companion object acts like a `case class`
  val employee = Employee("Tom", "Lee", "Programmer")
  println(employee.hireDate)
  // 2018-04-10

  val applyDepartment = Department.apply("apply()")
  val department = Department("Sports")
  println(applyDepartment)
  // Department(apply())
  println(department)
  // Department(Sports)
}


package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

// parameterized types on class
class _12ParamTypesOnClass extends FunSuite{
  test("Box") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty val lastName: String,
                   val title: String = "Programmer") {
      require(firstName.nonEmpty, "First Name cannot be empty")
      require(lastName.nonEmpty, "Last Name cannot be empty")
      require(title.nonEmpty, "Title cannot be empty")
      if (title.contains("Senior") || title.contains("Junior"))
        throw new IllegalArgumentException("Title cannot contain Junior or Senior")
    }
    case class Department(name: String)
    class Manager(firstName: String,
                  lastName: String,
                  title: String, val
                  department: Department) extends
      Employee(firstName, lastName, title)

    case class Box[T](t:T)
    val intBox = new Box(1) // Box[Int]
    println(intBox.t)
    // 1
    val intBox2:Box[Int] = Box(1) // Box[Int]
    val intBox3 = Box(1):Box[Int] // Coercion Box[Int]; Coercion means force
    val intResult:Int = intBox3.t
    println(intResult)
    // 1

    val stringBox = new Box("Hello")
    val stringBoxExplicit = Box[String]("Hello")
    println(stringBox.t)
    // hello
    println(stringBoxExplicit.t)
    // hello

    val managerBox = Box[Employee](
      new Manager("Tom", "Lee", "Programmer",
        new Department("Programming"))) // Box[Employee]
    println(managerBox.t.firstName)
    // Tom

  }

  test("Couple") {
    case class Couple[A, B](first:A, second:B)
    val coupleIntString = new Couple(10, "Scala") // Couple[Int, String]
    println(coupleIntString.first)
    println(coupleIntString.second)
  }

}

package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

class _07Subclassing extends FunSuite {
  test("subclassing") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty val lastName: String,
                   val title: String = "Programmer") {
      require(firstName.nonEmpty, "First Name cannot be empty")
      require(lastName.nonEmpty, "Last Name cannot be empty")
      require(title.nonEmpty, "Title cannot be empty")
      if (title.contains("Senior") || title.contains("Junior"))
        throw new IllegalArgumentException("Title cannot contain Junior or Senior")
    }

    class Department(val name: String)

    class Manager(firstName: String, lastName: String, title: String, val department: Department) extends
      Employee(firstName, lastName, title)

    val mathDepartment = new Department("Mathematics")
    val manager = new Manager("Tom", "Lee", "Mathematics", mathDepartment)
    println(manager.department.name)
    // Mathematics

    val managerRef:Employee = manager

    println(manager.firstName)
    println(managerRef.firstName)
    /*
Tom
Tom
     */

    println("Polymorphism")
    def extractFirstName(e:Employee) = {
      println(e.getClass.getName)
      // com.company.app._05Clazzes._07Subclassing$$anonfun$1$Manager$1
      e.firstName
    }

    println(extractFirstName(manager))
    println(extractFirstName(managerRef))
    /*
Tom
Tom
     */

  }

}

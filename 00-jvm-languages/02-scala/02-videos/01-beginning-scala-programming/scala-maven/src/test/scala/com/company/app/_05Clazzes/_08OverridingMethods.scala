package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

class _08OverridingMethods extends FunSuite {
  test("overriding methods") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty val lastName: String,
                   val title: String = "Programmer") {
      require(firstName.nonEmpty, "First Name cannot be empty")
      require(lastName.nonEmpty, "Last Name cannot be empty")
      require(title.nonEmpty, "Title cannot be empty")
      if (title.contains("Senior") || title.contains("Junior"))
        throw new IllegalArgumentException("Title cannot contain Junior or Senior")

      def fullName = s"$firstName $lastName"

      def copy(firstName: String = this.firstName, lastName: String = this.lastName, title: String = this.title) = {
        new Employee(firstName, lastName, title)
      }
    }

    class Department(val name: String)

    class Manager(firstName: String, lastName: String, title: String, val department: Department) extends
      Employee(firstName, lastName, title) {
      override def fullName: String = s"$firstName $lastName, ${department.name} Manager"
    }

    val mathDepartment = new Department("Mathematics")
    val manager = new Manager("Tom", "Lee", "Mathematics", mathDepartment)
    println(manager.department.name)
    // Mathematics

    val managerRef:Employee = manager
    println(managerRef.fullName)
    // Tom Lee, Mathematics Manager

  }

}

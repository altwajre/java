package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

class _03Constructors extends FunSuite {
  test("constructors") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty var lastName: String,
                   val title: String) {
      def this(firstName: String, lastName: String) = this(firstName, lastName, "Programmer")
    }
    val ada = new Employee("Ada", "Lovelace")
    println(ada.firstName)
    // Ada
    ada.lastName = "Lee"
    println(ada.lastName)
    // Lee
    println(ada.title)
    // Programmer
  }

  test("constructor named and default args") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty var lastName: String,
                   val title: String = "Programmer") {
    }
    val tom = new Employee(lastName="Lee", firstName="Tom")
    println(s"First name is ${tom.firstName}")
    println(s"Last name is ${tom.lastName}")
    /*
First name is Tom
Last name is Lee
     */
  }
}

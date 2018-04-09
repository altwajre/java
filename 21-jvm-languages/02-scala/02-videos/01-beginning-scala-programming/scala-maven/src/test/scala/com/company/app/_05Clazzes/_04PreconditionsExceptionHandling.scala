package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

class _04PreconditionsExceptionHandling extends FunSuite {
  test("Preconditions exceptions handling") {
    class Employee(@BeanProperty val firstName: String,
                   @BeanProperty val lastName: String,
                   val title: String = "Programmer") {
      require(firstName.nonEmpty, "First Name cannot be empty")
      require(lastName.nonEmpty, "Last Name cannot be empty")
      require(title.nonEmpty, "Title cannot be empty")
      if(title.contains("Senior") || title.contains("Junior"))
        throw new IllegalArgumentException("Title cannot contain Junior or Senior")
    }

    try {
      new Employee("Tom", "Singer")
    }
    catch {
      case iae:IllegalArgumentException => println(iae.getMessage)
    }
    finally {
      println(("Continuing with our program 1"))
    }
    // Continuing with our program 1

    try {
      new Employee("Tom", "Lee", "Senior C Programmer")
    } catch {
      case iae:IllegalArgumentException => println(iae.getMessage)
    } finally {
      println("Continuing with our program 2")
    }
    /*
Title cannot contain Junior or Senior
Continuing with our program 2
     */
  }

}

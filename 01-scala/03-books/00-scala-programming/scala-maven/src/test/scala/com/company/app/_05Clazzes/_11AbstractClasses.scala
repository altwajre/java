package com.company.app._05Clazzes

import org.scalatest.FunSuite

class _11AbstractClasses extends FunSuite {
  test("abstract classes") {
    abstract class Person {
      def firstName:String
      def lastName:String
    }
    case class Employee(firstName:String, lastName:String) extends Person {
    }

    val tom = Employee("Tom", "Lee")
    println(tom.firstName)
    // Tom

    println("polymorphism - assign subtype to abstract type")
    val tomPerson:Person = tom
    println(tomPerson.firstName)
    // Tom
  }

}

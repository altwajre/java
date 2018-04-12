package com.company.app._05Clazzes

import org.scalatest.FunSuite

import scala.beans.BeanProperty

class _02JavaGettersSetters extends FunSuite{
  test("Java Getters Setters") {
    class Employee(@BeanProperty val firstName:String, @BeanProperty var lastName:String)
    val ada = new Employee("Ada", "Lovelace")
    ada.lastName = "Lee"
    println(ada.lastName)
    // Lee
  }

}

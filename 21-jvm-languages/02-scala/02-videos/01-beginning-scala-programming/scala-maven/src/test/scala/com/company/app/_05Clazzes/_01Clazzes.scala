package com.company.app._05Clazzes

import org.scalatest.FunSuite

class _01Clazzes extends FunSuite{
  test("classes") {
    class Employee(val firstName:String, lastName:String)
    val ada = new Employee("Ada", "Lovelace")
    println(ada.firstName)
    // Ada
  }

}

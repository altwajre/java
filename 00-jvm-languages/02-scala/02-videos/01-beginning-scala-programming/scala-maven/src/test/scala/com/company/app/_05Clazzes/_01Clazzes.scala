package com.company.app._05Clazzes

class Employee(val firstName:String, lastName:String)

object RunnerApp extends App {
  val ada = new Employee("Ada", "Lovelace")
  println(ada.firstName)
  // Ada
}

package com.company.app._05Clazzes

import scala.beans.BeanProperty

class Employee2(@BeanProperty val firstName:String, @BeanProperty var lastName:String)

object RunnerApp2 extends App {
  val ada = new Employee2("Ada", "Lovelace")
  ada.lastName = "Lee"
  println(ada.lastName)
  // Lee
}

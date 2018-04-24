package com.company.app

import org.scalatest.FunSuite

class CustomerTest extends FunSuite {
  test("Customer Test") {
    val tom = new Customer("Tom", "Wang")
    println(s"Scala says Hello to ${tom.name}!")
  }
}

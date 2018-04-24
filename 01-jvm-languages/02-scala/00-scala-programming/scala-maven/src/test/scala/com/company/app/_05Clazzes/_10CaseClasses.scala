package com.company.app._05Clazzes

import org.scalatest.FunSuite

class _10CaseClasses extends FunSuite{

  test("compare two objects") {
    case class Department(name: String)

    val toys1 = Department("Toys")
    println(toys1)
    // Department(Toys)
    val toys2 = new Department("Toys")
    println(toys1 == toys2)
    // true
    println(toys1.hashCode == toys2.hashCode)
    // true
  }

  test("copy method") {
    case class Department(name: String)

    val toys1 = Department("Toys")
    val hardware = toys1.copy(name = "Hardware")
    println(hardware)
    // Department(Hardware)
  }

  test("override toString") {
    case class Department(name: String) {
      override def toString: String = s"Department: $name"
    }
    val toys = Department("Toys")
    println(toys)
    // Department: Toys
    val hardware = toys.copy(name = "Hardware")
    println(hardware)
    // Department: Hardware
  }

  test("pattern matching - like switch case") {
    case class Department(name: String) {
      override def toString: String = s"Department: $name"
    }

    val toys = Department("Toys")
    println(toys)
    // Department: Toys

    val name = toys match {
      case Department(n) => n + "!"
      case _ => "Unknown"
    }
    println(name)
    // Toys!

    val Department(name2) = toys
    println(name2)
    // Toys

  }

}

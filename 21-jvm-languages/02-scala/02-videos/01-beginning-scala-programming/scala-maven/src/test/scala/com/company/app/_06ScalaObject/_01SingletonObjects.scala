package com.company.app._06ScalaObject

import org.scalatest.FunSuite

class _01SingletonObjects extends FunSuite{
  test("singleton objects") {
    object MyMath {
      def add(x:Int, y:Int) = x + y
    }

    println(MyMath.add(2, 3))
    // 5

    case class Employee(firstName:String, lastName:String, title:String)

    object tom extends Employee("Tom", "Lee", "Programmer")
    println(tom.firstName)
    // Tom
  }

}

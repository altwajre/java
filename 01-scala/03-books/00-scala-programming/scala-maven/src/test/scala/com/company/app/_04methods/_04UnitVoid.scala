package com.company.app._04methods

import org.scalatest.FunSuite

// `Unit` means `void` in Java
class _04UnitVoid extends FunSuite{
  test("unit methods") {
    def add(x:Int, y:Int) = { // = means we want to return something
      x + y
    }
    println(add(2, 3))
    // 5

    def addUnit(x:Int, y:Int):Unit = x + y
    println(addUnit(2, 3))
    // ()
  }

}

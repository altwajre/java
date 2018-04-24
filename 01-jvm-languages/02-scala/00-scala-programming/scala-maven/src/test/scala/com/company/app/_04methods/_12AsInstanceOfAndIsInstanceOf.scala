package com.company.app._04methods

import org.scalatest.FunSuite

class _12AsInstanceOfAndIsInstanceOf extends FunSuite{
  test("isInstanceOf and asInstanceOf") {
    val g:Any = "Ice, ice, Call me Maybe"
    val h:String = g.asInstanceOf[String]
    println(h)
    // Ice, ice, Call me Maybe

    def decide(x:Any) = if (x.isInstanceOf[Int]) x.asInstanceOf[Int] + 1 else -1
    println(decide(8))
    // 9
    println(decide("Hello"))
    // -1
  }

}

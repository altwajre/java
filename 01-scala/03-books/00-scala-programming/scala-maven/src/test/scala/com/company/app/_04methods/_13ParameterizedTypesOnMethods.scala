package com.company.app._04methods

import org.scalatest.FunSuite

class _13ParameterizedTypesOnMethods extends FunSuite{
  test("parameterized types on methods") {
    def decide(b:Boolean, x:Any, y:Any) = if (b) x else y
    println(decide(true, "A", "B"))
    // A
    println(decide(false, 1, 0))
    // 0
  }
  test("generic type method") {
    def decide[T](b:Boolean, x:T, y:T) = if (b) x else y
    println(decide(true, "A", "B"))
    // A
    println(decide(false, 1, 0))
    // 0
  }
}

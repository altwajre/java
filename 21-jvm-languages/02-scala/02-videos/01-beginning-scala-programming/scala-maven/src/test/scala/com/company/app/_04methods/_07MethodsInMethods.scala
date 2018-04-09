package com.company.app._04methods

import org.scalatest.FunSuite

class _07MethodsInMethods extends FunSuite{
  test("methods in methods") {
    def factorial(n:Int) = {
      def fact(n:BigInt, acc:BigInt):BigInt = {
        if(n == 0 || n == 1)acc
        else fact(n-1, acc * n)
      }
      fact(n, 1)
    }
    println(factorial(5))
    // 120
  }

}

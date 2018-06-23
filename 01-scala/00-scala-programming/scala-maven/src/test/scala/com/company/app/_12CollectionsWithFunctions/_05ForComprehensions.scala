package com.company.app._12CollectionsWithFunctions

object _05ForComprehensions extends App {
  for (i <- 1 to 3) {
    print("%s ".format(i))
  }
  // 1 2 3
  println

  // For Comprehensions
  val result1 = for (i <- 1 to 3) yield (i * 10)
  println(result1)
  // Vector(10, 20, 30)

  val result2 = (1 to 3).map(i => i * 10)
  println(result2)
  // Vector(10, 20, 30)

  val result3 = for (i <- List(1, 2, 3);
                     j <- List(11, 22, 33)) yield (i , j)
  println(result3)
  // List((1,11), (1,22), (1,33), (2,11), (2,22), (2,33), (3,11), (3,22), (3,33))

}

package com.company.app._09Tuples

object Tuples extends App {
  val t = (8, "Cool", 3.14)
  println(t._1)
  // 8
  println(t._2)
  // Cool
  println(t._3)
  // 3.14

  val t1:(Int, String, Double) = t
  println(t1._1)
  // 8
  val t2:Tuple3[Int, String, Double] = t

  case class Department(name:String)
  val u = ("8", Department("QA"))
  println(u)
  // (8,Department(QA))
  private val u2: (Department, String) = u.swap
  println(u2)
  // (Department(QA),8)
}

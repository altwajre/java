object Tuples extends App {
  val t = (8, "Cool", 3.14)
  println(t._1)
  println(t._2)
  println(t._3)

  val t1:(Int, String, Double) = t
  val t2:Tuple3[Int, String, Double] = t

  case class Department(name:String)
  val u = ("8", Department("QA"))
  println(u)
  val u2 = u.swap
  println(u2)
  println(u) //stays the same
}

//$ scala Tuples.scala
//8
//Cool
//3.14
//(8,Department(QA))
//(Department(QA),8)
//(8,Department(QA))

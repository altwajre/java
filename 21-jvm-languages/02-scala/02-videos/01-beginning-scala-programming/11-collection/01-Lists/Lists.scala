object Lists extends App {
  val a = List(1,2,3,4,5)
  val a2 = List.apply(1,2,3,4,5)
  val a3 = 1 :: 2 :: 3 :: 4 :: 5 :: Nil

  println(a.head) //1
  println(a.tail) //2,3,4,5
  println(a.init) //1,2,3,4
  println(a.last) //5

  println("#index")
  println(a.apply(3)) // 3 is index
  println(a.min)
  println(a.max)
  println(a.isEmpty)
  println(a.nonEmpty)
  println(a.updated(3, 18)) //Underused

  println("#mkString")
  println(a.mkString(","))
  println(a.mkString("**"))
  println(a.mkString("[", "**", "]"))

}

//$ scala Lists.scala
//1
//List(2, 3, 4, 5)
//List(1, 2, 3, 4)
//5
//#index
//4
//1
//5
//false
//true
//List(1, 2, 3, 18, 5)
//#mkString
//1,2,3,4,5
//1**2**3**4**5
//[1**2**3**4**5]

object ForComprehensions extends App {
  for (i <- 1 to 3) {
    print("%s ".format(i))
  } //1 2 3
  println

  val result1 = for (i <- 1 to 3) yield (i * 10) // For Comprehensions
  println(result1) //Vector(10, 20, 30)

  val result2 = (1 to 3).map(i => i * 10)
  println(result2) //Vector(10, 20, 30)

  println("#Option")
  val result3 = for (i <- Some(8)) yield (i + 10)
  println(result3) //Some(18)

  val result4 = Some(8).map(i => i + 10)
  println(result4)

  val result5 = for (i <- List(1, 2, 3);
                     j <- List(11, 22, 33)) yield (i, j)
  println(result5) //List((1,11), (1,22), (1,33), (2,11), (2,22), (2,33), (3,11), (3,22), (3,33))

  val result6 = List(1, 2, 3).flatMap(i => List(11, 22, 33).map(j => (i, j)))
  println(result6) //List((1,11), (1,22), (1,33), (2,11), (2,22), (2,33), (3,11), (3,22), (3,33))

  val result7 = for (i <- List(1, 2, 3)
                     if (i % 2) == 0;
                     j <- List(11, 22, 33)) yield (i, j)
  println(result7) //List((2,11), (2,22), (2,33))

  val result8 = for (i <- List(1, 2, 3);
                     j <- List(11, 22, 33)
                     if (j < 30)) yield (i, j)
  println(result8) //List((1,11), (1,22), (2,11), (2,22), (3,11), (3,22))

  val result9 = List(1,2,3).filter(i => i % 2 == 0).flatMap(i => List(11,22,33).map(j => (i, j)))
  println(result9) //List((2,11), (2,22), (2,33))

  val result10 = List(1,2,3).flatMap(i => List(11,22,33).withFilter(j => j < 30).map(j => (i,j)))
  println(result10) //List((1,11), (1,22), (2,11), (2,22), (3,11), (3,22))

}
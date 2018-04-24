object FlatMap extends App {
  val a = List(1,2,3)
  println(a.map(x => List(-x, 0, x)))
  //List(List(-1, 0, 1), List(-2, 0, 2), List(-3, 0, 3))
  println(a.map(x => List(-x, 0, x)).flatten)
  //List(-1, 0, 1, -2, 0, 2, -3, 0, 3)
  println(a.flatMap(x => List(-x, 0, x)))
  //List(-1, 0, 1, -2, 0, 2, -3, 0, 3)

  val b:List[List[List[Int]]] = List(List(List(1,2,3), List(4,5,6)),
                                     List(List(11,12,13), List(14,15,16)))
  println(b)
  //List(List(List(1, 2, 3), List(4, 5, 6)), List(List(11, 12, 13), List(14, 15, 16)))
  println(b.flatMap(c => c))
  //List(List(1, 2, 3), List(4, 5, 6), List(11, 12, 13), List(14, 15, 16))
  println(b.flatMap(c => c).flatMap(c => c))
  //List(1, 2, 3, 4, 5, 6, 11, 12, 13, 14, 15, 16)

  val s = Set(1,2,3)
  println(s.map(x => Set(x, x * 2)))
  //Set(Set(1, 2), Set(2, 4), Set(3, 6))
  println(s.flatMap(x => Set(x, x * 2)))
  //Set(1, 6, 2, 3, 4)

  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three")
  println(m.map(t => Map(t._1 -> t._2, (t._1 * 10) -> (t._2 + " Hundred"))))
  //List(Map(1 -> One, 10 -> One Hundred), Map(2 -> Two, 20 -> Two Hundred), Map(3 -> Three, 30 -> Three Hundred))
  println(m.flatMap(t => Map(t._1 -> t._2, (t._1 * 10) -> (t._2 + " Hundred"))))
  //Map(10 -> One Hundred, 20 -> Two Hundred, 1 -> One, 2 -> Two, 3 -> Three, 30 -> Three Hundred)

  println(Some(8).map(x => Some(x + 10)))
  //Some(Some(18))
  println(Some(8).flatMap(x => Some(x + 10)))
  //Some(18)

  println(Some(8).map(x => None))
  //Some(None)
  println(Some(8).flatMap(x => None))
  //None

  //x => x in map() is identity function
  println(List(Some(8), None, Some(1), None, Some(18)).map(x => x))
  //List(Some(8), None, Some(1), None, Some(18))
  println(List(Some(8), None, Some(1), None, Some(18)).flatMap(x => x))
  //List(8, 1, 18)

}

object Sets extends App {
  val set =  Set.apply(1,2,3,4)
  val set2 = Set(1,2,3,4,5)
  val set3 = Set(1,2,3,4,5,6)
  val set4 = Set(1,2,3,4,5,6,6,7)

  println(set) //Set(1, 2, 3, 4)
  println(set2) //Set(5, 1, 2, 3, 4)
  println(set3) //Set(5, 1, 6, 2, 3, 4)
  println(set4) //Set(5, 1, 6, 2, 7, 3, 4)

  val set5 = Set(1,2)
  println(set diff set4) //empty Set
  println(set4 diff set) //Set(5,6,7)
  println(set union set3) //Set(1,2,3,4,5,6)
  println(set intersect set3) //Set(1,2)

  println(set ++ set2) //(1,2,3,4,5) works as union
  println(set ++ List(15,19,20)) //(1,2,3,4,5,15,19,20)

  println(set -- set5) //collection uses --
  println(set -- Set(3,4,5)) //Set(1, 2)
  println(set - 3) //single item uses -
}

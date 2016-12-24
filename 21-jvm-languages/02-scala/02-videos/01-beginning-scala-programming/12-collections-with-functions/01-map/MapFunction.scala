object MapFunction extends App {
  val a = List(1,2,3,4,5)
  val f = (x:Int) => x + 1
  println(a.map(f)) //List(2, 3, 4, 5, 6)
  println(a.map((x:Int) => x + 1)) //List(2, 3, 4, 5, 6)
  println(a.map(x => x + 1)) //List(2, 3, 4, 5, 6)
  println(a.map(_ + 1)) //List(2, 3, 4, 5, 6)
  println(a.map(1 + _)) //List(2, 3, 4, 5, 6)

  val b = Set("Brown", "Red", "Green", "Purple", "Gray", "Yellow")
  println(b.map(s => s.size)) //Set(5, 6, 3, 4)
  println(b.map(_.size)) //Set(5, 6, 3, 4)
  println(b.map(s => (s, s.size))) //Set((Green,5), (Brown,5), (Gray,4), (Red,3), (Yellow,6), (Purple,6))

  val fifaMensChamps = Map('Germany -> 4, 'Brazil -> 5, 'Italy -> 4, 'Argentina -> 2)
  println(fifaMensChamps.map(t => (Symbol.apply("Team " + t._1.name),t._2))) //Map('Team Germany -> 4, 'Team Brazil -> 5, 'Team Italy -> 4, 'Team Argentina -> 2)

  println("Hello!".map(c => (c + 1).toChar)) //Ifmmp"
  println(Some(4).map(1+)) //Some(5)
  println(None.asInstanceOf[Option[Int]].map(1+)) //None

  val age:Option[Int] = None
  println(age.map(1+)) //None
}

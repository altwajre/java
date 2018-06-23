object Arrays extends App {
  val a:Array[Int] = Array(1,2,3,4) //int[]

  println(a.head) //1
  println(a.tail) //[I@200a570f
  println(a.init) //[I@16b3fc9e
  println(a.last) //4
  println(a.apply(2)) //3
  println(a.max) //4
  println(a.isEmpty) //false
}

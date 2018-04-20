object Zip extends App {
  val a = List(1,2,3,4)
  val b = List('a','b','c','d')
  println(a zip b) //List((1,a), (2,b), (3,c), (4,d))

  println((1 to 5) zip ('a' to 'c')) //Vector((1,a), (2,b), (3,c))
  println((1 to 4) zip ('a' to 'z')) //Vector((1,a), (2,b), (3,c), (4,d))
}

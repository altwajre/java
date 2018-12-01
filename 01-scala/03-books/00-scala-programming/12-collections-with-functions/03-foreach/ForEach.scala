object ForEach extends App {
  val a = 1 to 3
  a.foreach(x => print("%s ".format(x)))

  println("\n#println _")

  a.foreach(println _)

  println("#a foreach println")
  a foreach println
}
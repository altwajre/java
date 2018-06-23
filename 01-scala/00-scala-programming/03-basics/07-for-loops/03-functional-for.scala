val xs = List(1,2,3,4)
val result = for (a <- xs) yield (a + 1) // for comprehensions
println(result)

//$ scala 03-functional-for.scala
//List(2, 3, 4, 5)

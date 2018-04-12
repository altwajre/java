val result = (1 to 10).reverse.mkString(", ") // mkString means makeString
println(result)

println((10 to 1 by -1).mkString(", "))

//$ scala 02-functional-loop.scala
//10, 9, 8, 7, 6, 5, 4, 3, 2, 1
//10, 9, 8, 7, 6, 5, 4, 3, 2, 1

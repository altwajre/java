val xs = List(1,2,3,4)
var result = List[Int]() // [Int] means generic type int <int> in Java
for (a <- xs) {
  result = result :+ (a + 1) // :+ means append
}
println(result)

//$ scala 02-imperative-for.scala
//List(2, 3, 4, 5)

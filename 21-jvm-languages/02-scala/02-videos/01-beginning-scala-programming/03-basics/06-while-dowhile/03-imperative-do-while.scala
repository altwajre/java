var a = 10
var result = ""
do {
  result += a
  if (a > 1) result += ", "
  a -= 1
} while (a > 0)

println(result)

//$ scala 03-imperative-do-while.scala
//10, 9, 8, 7, 6, 5, 4, 3, 2, 1

var a = 10
var result = ""
while ( a > 0) {
  result = result + a
  if ( a > 1) result += ", "
  a = a - 1
}
println(result)

//$ scala 01-imperative-while.scala
//10, 9, 8, 7, 6, 5, 4, 3, 2, 1

var result = ""
for (a <- 1 to 10){
  result += a
  if (a < 10) result += ", "
}
println(result)

//$ scala 01-imperative-for.scala
//1, 2, 3, 4, 5, 6, 7, 8, 9, 10

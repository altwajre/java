// warning occurs on uninitialized value b

val a = 10 + b
val b = 19
println(a)

//$ scala 01-Vals.scala
//Vals.scala:1: warning: Reference to uninitialized value b
//val a = 10 + b
//             ^
//one warning found
//10

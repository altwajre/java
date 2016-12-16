// use lazy to wire uninitialized value b

lazy val a = 10 + b
lazy val b = 19
println(a)

//$ scala 02-LazyVals.scala
//29

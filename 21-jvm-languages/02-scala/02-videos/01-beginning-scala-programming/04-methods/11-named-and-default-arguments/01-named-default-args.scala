def processNumbers(b:Boolean = true, x:Int, y:Int) = if (b) x else y

println(processNumbers(x = 10, y = 41))

def add(x:Int, y:Int = 10) = x + y
println(add(4,3))
println(add(20))

//$ scala 01-named-default-args.scala
//10
//7
//30

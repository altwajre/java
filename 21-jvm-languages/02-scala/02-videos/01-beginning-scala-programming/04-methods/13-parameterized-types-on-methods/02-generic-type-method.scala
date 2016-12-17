def decide[T](b:Boolean, x:T, y:T) = if (b) x else y

println(decide(true, "A", "B"))
println(decide(false, 1, 0))
println(decide(true, 'a', 'b'))

def getNextChar(c:Char) = (c + 1).toChar
println(getNextChar(decide(true, 'a', 'b')))

//output:
//$ scala 02-generic-type-method.scala
//A
//0
//a
//b

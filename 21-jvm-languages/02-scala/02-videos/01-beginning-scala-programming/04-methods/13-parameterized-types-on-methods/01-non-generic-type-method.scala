def decide(b:Boolean, x:Any, y:Any) = if (b) x else y

println(decide(true, "A", "B"))
println(decide(false, 1, 0))
println(decide(true, 'a', 'b'))

def getNextChar(c:Char) = (c + 1).toChar
println(getNextChar(decide(true, 'a', 'b')))

//output:
//$ scala 01-non-generic-type-method.scala
///Users/whan/Desktop/java/21-jvm-languages/02-scala/02-videos/01-beginning-scala-programming/04-methods/13-parameterized-types-on-methods/01-non-generic-type-method.scala:8: error: type mismatch;
//found   : Any
//required: Char
//println(getNextChar(decide(true, 'a', 'b')))
//^
//one error found

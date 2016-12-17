def addNum(x:Int) = x + 1
def addNum(y:Double) = y + 30.0
def addNum(z:String) = z + 19

println(addNum(10))
println(addNum(20.0))
println(addNum("Hello "))

//$ scala 01-method-overloading.scala
//11
//50.0
//Hello 19

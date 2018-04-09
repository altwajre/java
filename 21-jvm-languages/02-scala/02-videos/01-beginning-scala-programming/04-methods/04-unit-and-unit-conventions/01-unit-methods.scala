def add(x:Int, y:Int) = { // = means we want to return something
  x + y
}

def badAdd(x:Int, y:Int) {
  x + y
}

def addUnit(x:Int, y:Int):Unit = x + y

println(badAdd(4,5))

println(addUnit(2,3))
// ()

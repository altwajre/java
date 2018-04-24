object RepeatedParameters extends App {
  def repeatedParameterMethod(x:Int, y:String, z:Any*) = { // z:Any* is the repeated parameters
    println(z) //WrappedArray
    "%d %ss give you %s".format(x, y, z.mkString(", "))
  }

  println(repeatedParameterMethod(3, "BMW", "3 series", "all wheel drive", "black color"))
  println(repeatedParameterMethod(3, "BMW", List("3 series", "all wheel drive", "black color")))
  println(repeatedParameterMethod(3, "BMW", List("3 series", "all wheel drive", "black color"):_*))
}

//$ scala RepeatedParameters.scala
//WrappedArray(3 series, all wheel drive, black color)
//3 BMWs give you 3 series, all wheel drive, black color
//WrappedArray(List(3 series, all wheel drive, black color))
//3 BMWs give you List(3 series, all wheel drive, black color)
//List(3 series, all wheel drive, black color)
//3 BMWs give you 3 series, all wheel drive, black color
